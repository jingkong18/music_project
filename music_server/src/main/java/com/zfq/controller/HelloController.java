package com.zfq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName HelloController
 * @Author zfq
 * @Date 2025/12/8
 */

@Api(tags = "swagger接口")
@RestController
@RequestMapping("/test/hello")
public class HelloController {
    @ApiOperation(value = "Hello请求", notes = "Hello查看")
    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
