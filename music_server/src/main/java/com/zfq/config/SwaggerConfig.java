package com.zfq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Author zfq
 * @Date 2025/12/7
 */
@Configuration
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {
    /**
     * 配置基本信息
     * @return
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Restful API")
                .description("Restful API")
                .termsOfServiceUrl("")
                .contact(new Contact("zfq","",""))
                .version("1.0")
                .build();
    }

    /**
     * 配置文档生成最佳实践
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("zfq")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
