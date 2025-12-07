package com.zfq.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SwaggerConfig
 * @Author zfq
 * @Date 2025/12/7
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI musicProjectOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("音乐项目接口文档")
                        .description("音乐项目接口文档")
                        .version("1.0"));
    }
}
