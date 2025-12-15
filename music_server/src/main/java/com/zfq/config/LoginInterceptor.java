package com.zfq.config;

import com.zfq.utils.UserHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInterceptor
 * @Author zfq
 * @Date 2025/12/7
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 让登录请求直接放行，不检查用户信息
        if ("/user/login".equals(request.getRequestURI())) {
            return true;
        }

        if (UserHolder.getUser()== null){
            response.setStatus(401);
            return false;
        }

        return true;
    }
}
