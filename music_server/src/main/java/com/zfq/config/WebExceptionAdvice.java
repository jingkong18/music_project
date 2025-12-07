package com.zfq.config;

import com.zfq.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WebExceptionAdvice
 * @Author zfq
 * @Date 2025/12/7
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public Result error(HttpServletRequest request, Exception e) {
        log.error("异常信息：{}", e.getMessage());
        return Result.error("服务器异常");
    }
}
