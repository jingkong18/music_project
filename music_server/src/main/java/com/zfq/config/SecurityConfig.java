package com.zfq.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.zfq.utils.TokenAuthenticationFilter;
import com.zfq.utils.TokenManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @ClassName SecurityConfig
 * @Author zfq
 * @Date 2025/12/13
 */
@Configuration
@EnableWebSecurity
@EnableKnife4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private TokenManager tokenManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;


    // 第一步：配置全局CORS过滤器（核心跨域规则）
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 1. 允许的源（开发环境用前端项目的地址，生产环境替换为具体域名，如https://www.xxx.com）
        // 注意：如果需要允许携带Cookie（credentials: true），这里不能用*，必须指定具体源
        config.addAllowedOrigin("http://localhost:5173"); // 比如Vue前端的默认端口
        config.addAllowedOrigin("http://localhost:8080"); // 比如React前端的默认端口

        // 2. 允许携带Cookie（如果前端需要传递Cookie/Token，必须开启）
        config.setAllowCredentials(true);

        // 3. 允许的请求方法（GET/POST/PUT/DELETE/OPTIONS，OPTIONS是预检请求）
        config.setAllowedMethods(Arrays.asList(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name()
        ));

        // 4. 允许的请求头（*表示所有，也可以指定具体头，如Token/Content-Type）
        config.setAllowedHeaders(Arrays.asList("*"));

        // 5. 预检请求的缓存时间（秒）：减少OPTIONS请求的次数，提升性能
        config.setMaxAge(3600L);

        // 6. 对所有路径应用跨域规则
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 第二步：启用CORS（必须放在最前面，关联上面的CorsFilter）
            .cors().and()
            .authorizeRequests()
            // 放行Swagger 3.x/ Knife4j相关路径
            .antMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/doc.html" // Knife4j的UI入口（如果用了Knife4j，需要加这个）
            ).permitAll()
            // 放行用户相关接口（修复拼写错误：loogin → login）
            .antMatchers("/user/add","/user/login","/user/resetPasswd").permitAll()
            // 显式放行OPTIONS预检请求（兜底，即使anyRequest().permitAll()也建议加）
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 开发环境放行所有请求（生产环境建议改为anyRequest().authenticated()）
            .anyRequest().permitAll()
            .and()
            // 关闭CSRF（开发环境简化，生产环境按需开启）
            .csrf().disable()
            // 关闭默认的表单登录
            .formLogin().disable();

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
