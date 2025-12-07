package com.zfq.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Author zfq
 * @Date 2025/12/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "统一返回结果封装类")
public class Result {
    @Schema(description = "状态码")
    private String code;
    @Schema(description = "返回信息")
    private String message;
    @Schema(description = "返回数据")
    private Object data;
    @Schema(description = "是否成功")
    private Boolean success;
    @Schema(description = "返回类型")
    private String type;

    public static Result success(String  message) {
        Result result = new Result();
        result.setCode("200");
        result.setMessage(message);
        result.setSuccess(true);
        result.setType("success");
        result.setData(null);
        return result;
    }

    public static Result success(String  message, Object data) {
        Result result = success(message);
        result.setData(data);
        return result;
    }

    public static Result error(String  message) {
        Result result = success(message);
        result.setSuccess(false);
        result.setType("error");
        return result;
    }

    public static Result warning(String  message) {
        Result result = error(message);
        result.setType("warning");
        return result;
    }

    public static Result fatal(String  message) {
        Result result = error(message);
        result.setCode("500");
        return result;
    }

}
