package com.zfq.controller;

import com.zfq.common.Result;
import com.zfq.model.dto.UserDTO;
import com.zfq.model.po.User;
import com.zfq.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    public Result add(@RequestBody User  user){
        return userService.add(user);
    }

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO user, HttpSession session){
        return userService.login(user, session);
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping("/resertPasswd")
    public Result resertPasswd(@RequestBody UserDTO user){
        return userService.resertPasswd(user);
    }

    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @GetMapping("/sendCode")
    public Result sendCode(String email){
        return userService.sendCode(email);
    }





}
