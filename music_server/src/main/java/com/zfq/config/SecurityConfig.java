package com.zfq.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName SecurityConfig
 * @Author zfq
 * @Date 2025/12/13
 */
@Configuration
@EnableWebSecurity
@EnableKnife4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            // 放行Swagger 3.x的所有相关路径
            .antMatchers(
                "/swagger-ui/**",        // Swagger UI界面
                "/v3/api-docs/**",       // OpenAPI接口文档数据
                "/swagger-resources/**", // Swagger资源列表
                "/webjars/**"            // Swagger依赖的静态资源
            ).permitAll()
            // 其他请求根据需求配置（开发环境可暂时放行所有）
            .anyRequest().permitAll()
            .and()
            // 关闭CSRF（开发环境简化，生产环境按需开启）
            .csrf().disable()
            // 关闭默认的表单登录（如果不需要认证）
            .formLogin().disable();
    }
}
