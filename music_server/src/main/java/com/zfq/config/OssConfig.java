package com.zfq.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ClassName OssConfig
 * @Author zfq
 * @Date 2025/12/8
 */
@Configuration
@Component
@Data
public class OssConfig {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Bean
    public OSS ossClient(){
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }


}
