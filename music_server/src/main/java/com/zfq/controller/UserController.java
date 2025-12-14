package com.zfq.controller;

import com.zfq.common.Result;
import com.zfq.model.dto.UserDTO;
import com.zfq.model.po.User;
import com.zfq.model.vo.UserInfoVO;
import com.zfq.service.IUserService;
import com.zfq.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Author zfq
 * @Date 2025/12/7
 */
@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/add")
    public Result add(@RequestBody UserDTO  user){
        return userService.add(user);
    }

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO user){
        return userService.login(user);
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping("/resertPasswd")
    public Result resertPasswd(@RequestBody UserDTO user){
        return userService.resertPasswd(user);
    }

    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @GetMapping("/sendCode")
    public Result sendCode(@RequestParam String email){
        return userService.sendCode(email);
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @GetMapping("/info")
    public Result getUserInfo(@RequestHeader("Authorization") String token){
        // 1. 解析Token得到user_id
        String userId = TokenUtil.parseToken(token);
        if (userId == null) {
            return Result.error("无效的token");
        }
        
        // 2. 查询数据库
        User user = userService.getById(Long.parseLong(userId));
        if (user == null) {
            return Result.error("用户不存在");
        }
        
        // 3. 脱敏（隐藏敏感信息，如手机号）
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(user, vo);
        
        // 4. 返回结果
        return Result.success("获取用户信息成功",vo);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token){
        // 去除Bearer前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // 删除token
        userService.logout(token);
        return Result.success("退出登录成功");
    }





}
