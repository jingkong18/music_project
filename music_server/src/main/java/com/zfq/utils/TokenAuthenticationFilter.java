package com.zfq.utils;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName TokenAuthenticationFilter
 * @Author zfq
 * @Date 2025/12/14
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;

    private final UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenManager tokenManager, UserDetailsService userDetailsService) {
        this.tokenManager = tokenManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/user/login") || request.getServletPath().equals("/user/register") || request.getServletPath().equals("/user/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 从请求头获取Token（格式：Bearer {token}）
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // 去除Bearer前缀
        }

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(String.format("{\"code\":401,\"msg\":\"%s\"}","Token无效，无法解析用户名"));
            writer.flush();
            writer.close();
            return;
        }
        // 从Redis中获取用户名
        String username = tokenManager.getUsernameFromToken(token);

        if (username == null || username.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(String.format("{\"code\":401,\"msg\":\"%s\"}","Token无效，无法解析用户名"));
            writer.flush();
            writer.close();
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // 加载用户详情
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // 创建认证对象并设置到SecurityContext（Spring Security认证核心）
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 刷新Token过期时间（可选）
            tokenManager.refreshToken(token);
        }

        // 继续执行过滤器链
        filterChain.doFilter(request, response);
    }
}