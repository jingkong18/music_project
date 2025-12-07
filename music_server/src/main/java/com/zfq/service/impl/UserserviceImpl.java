package com.zfq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfq.common.Result;
import com.zfq.mapper.IUserMapper;
import com.zfq.model.dto.UserDTO;
import com.zfq.model.po.User;
import com.zfq.service.ISendCodeService;
import com.zfq.service.IUserService;
import com.zfq.utils.RandomUtils;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static com.zfq.constant.Contants.SALT;
import static com.zfq.constant.RedisConstants.LOGIN_CODE_KEY;

/**
 * @ClassName UserserviceImpl
 * @Author zfq
 * @Date 2025/12/7
 */
@Service
public class UserserviceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ISendCodeService sendCodeService;

    /**
     * @param user
     * @return com.zfq.common.Result
     * @description: 注册
     * @author zfq
     * @date 2025/12/7
     **/
    @Override
    public Result add(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nick_name", user.getNickName());
        Long num = userMapper.selectCount(queryWrapper);
        if (num > 0) {
            return Result.warning("用户名已存在");
        }
        // MD5密码加密
        String password = DigestUtils.md5DigestAsHex((SALT + user.getPassword()).getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);

        if (StringUtil.isBlank(user.getPhone())){
            return Result.warning("手机号不能为空");
        }
        if (StringUtil.isBlank(user.getEmail())){
            return Result.warning("邮箱不能为空");
        }

        user.setIcon("img/avator/user.jpg");
        user.setStatus(1L);

        try{
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("phone", user.getPhone());
            User user1 = userMapper.selectOne(queryWrapper1);
            if (user1 != null){
                return Result.warning("手机号不允许重复");
            }
            QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("email", user.getEmail());
            User user2 = userMapper.selectOne(queryWrapper2);
            if (user2 != null){
                return Result.warning("邮箱不允许重复");
            }
            if (userMapper.insert(user) > 0){
                return Result.success("注册成功");
            }else {
                return Result.error("注册失败");
            }
        } catch (DuplicateKeyException e){
            return Result.fatal(e.getMessage());
        }
    }

    @Override
    public Result login(User user, HttpSession session) {
        if(user.getStatus() == 2L){
            return Result.warning("用户被禁用");
        }

        String nickName = user.getNickName();
        String password = user.getPassword();

        if (verityPasswd(nickName, password)){
            session.setAttribute("user", nickName);
            return Result.success("登录成功", userMapper.selectList(new QueryWrapper<>( user)));
        }else {
            return Result.warning("用户名或密码错误");
        }
    }

    @Override
    public Result loginEmail(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        User user1 = findByEmail(email);

        if (verityPasswd(user1.getNickName(), password)){
            return Result.success("登录成功", userMapper.selectList(new QueryWrapper<>( user)));
        }else {
            return Result.error("用户名或密码错误");
        }
    }

    @Override
    public Result resertPasswd(UserDTO user) {
        User user1 = findByEmail(user.getEmail());
        String code = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY);
        if (user1 == null){
            return Result.warning("用户不存在");
        } else if (!code.equals(user.getCode())) {
            return Result.warning("验证码错误");
        }
        String secretPasswd = DigestUtils.md5DigestAsHex((SALT + user.getPassword()).getBytes(StandardCharsets.UTF_8));
        user.setPassword(secretPasswd);

        if (userMapper.updateById(user)>0){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }

    @Override
    public Result sendCode(String email) {
        User user = findByEmail(email);
        if (user == null){
            return Result.fatal("用户不存在");
        }
        String code = RandomUtils.code();

        sendCodeService.sendCode(code, email);

        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY, code,5, TimeUnit.MINUTES);
        return Result.success("验证码发送成功");

    }

    public boolean verityPasswd(String nickname, String passwd){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nick_name", nickname);
        String secretPasswd = DigestUtils.md5DigestAsHex((SALT + passwd).getBytes(StandardCharsets.UTF_8));
        queryWrapper.eq("password", secretPasswd);
        return userMapper.selectCount(queryWrapper) > 0;
    }

    public User findByEmail(String  email){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectOne(queryWrapper);
    }
}
