package com.zfq.common;


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

public class Result {

    private String code;

    private String message;

    private Object data;

    private Boolean success;

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
