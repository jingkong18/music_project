package com.zfq.controller;

import com.zfq.common.Result;
import com.zfq.model.dto.UserDTO;
import com.zfq.model.po.User;
import com.zfq.service.IUserService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Author zfq
 * @Date 2025/12/7
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Schema(description = "用户接口")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User  user){
        return userService.add(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session){
        return userService.login(user, session);
    }

    @PostMapping("/email")
    public Result email(@RequestBody User user, HttpSession session){
        return userService.loginEmail(user);
    }

    @PostMapping("/resertPasswd")
    public Result resertPasswd(@RequestBody UserDTO user){
        return userService.resertPasswd(user);
    }

    @GetMapping("/sendCode")
    public Result sendCode(String email){
        return userService.sendCode(email);
    }



}
