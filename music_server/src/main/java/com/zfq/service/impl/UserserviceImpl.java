package com.zfq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zfq.common.Result;
import com.zfq.mapper.IUserMapper;
import com.zfq.model.dto.UserDTO;
import com.zfq.model.po.User;
import com.zfq.service.ISendCodeService;
import com.zfq.service.IUserService;
import com.zfq.utils.BCryptUtil;
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
import java.util.List;
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
     * 注册
     **/
    @Override
    public Result add(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nick_name", user.getNickName());
        Long num = userMapper.selectCount(queryWrapper);
        if (num > 0) {
            return Result.warning("用户名已存在");
        }
        if (StringUtil.isBlank(user.getPhone())){
            return Result.warning("手机号不能为空");
        }
        if (StringUtil.isBlank(user.getEmail())){
            return Result.warning("邮箱不能为空");
        }
        // 密码加密
        String password = BCryptUtil.encrypt(user.getPassword());
        user.setPassword(password);

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
    /**
     * 登录
     **/
    @Override
    public Result login(UserDTO user, HttpSession session) {

        String account = user.getAccount();
        String password = user.getPassword();

        if (account == null || account.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return Result.warning("账号或密码不能为空");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
            .or().eq("nick_name", account) // 匹配用户名（数据库字段名：nick_name，根据你的表调整）
            .or().eq("phone", account)      // 匹配手机号（数据库字段名：phone）
            .or().eq("email", account);     // 匹配邮箱（数据库字段名：email）


        List<User> userList = userMapper.selectList(queryWrapper);
        if (userList == null || userList.isEmpty()){
            return Result.warning("账号或密码错误");
        }

        User user1 = userList.get(0);

        if(user1.getStatus() == 2L){
            return Result.warning("用户被禁用");
        }

        if (BCryptUtil.matches(password, user1.getPassword())){
            return Result.success("登录成功", user);
        }else {
            return Result.warning("账号或密码错误");
        }


    }

    /**
     * 忘记密码
     **/
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
    /**
     * 发送验证码
     **/
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

    public User findByEmail(String  email){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectOne(queryWrapper);
    }

}
