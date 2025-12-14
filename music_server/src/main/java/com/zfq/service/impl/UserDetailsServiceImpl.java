package com.zfq.service.impl;

import com.zfq.model.po.User;
import com.zfq.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @ClassName UserDetailsServiceImpl
 * @Author zfq
 * @Date 2025/12/14
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username实际上是用户ID（从token中解析出来的）
        try {
            Long userId = Long.parseLong(username);
            User user = userService.getById(userId);
            
            if (user == null) {
                throw new UsernameNotFoundException("用户不存在: " + username);
            }
            
            // 创建Spring Security的User对象
            return new org.springframework.security.core.userdetails.User(
                user.getId().toString(), // 用用户ID作为用户名
                user.getPassword(),
                new ArrayList<>() // 权限列表，暂时为空
            );
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("无效的用户ID: " + username);
        }
    }
}