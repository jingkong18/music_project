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

public class Result<T> {

    private String code;

    private String message;

    private T data;

    private Boolean success;

    private String type;

    public static <T> Result<T> success(String message) {
        return success(message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(true);
        result.setType("success");
        return result;
    }

    // ==================== 静态工厂方法：失败场景 ====================
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode("400"); // 错误默认业务码（可根据需求调整）
        result.setMessage(message);
        result.setData(null);
        result.setSuccess(false);
        result.setType("error");
        return result;
    }

    public static <T> Result<T> warning(String message) {
        Result<T> result = new Result<>();
        result.setCode("300"); // 警告默认业务码（可根据需求调整）
        result.setMessage(message);
        result.setData(null);
        result.setSuccess(false); // 警告也视为非成功场景（可根据需求调整）
        result.setType("warning");
        return result;
    }

    public static <T> Result<T> fatal(String message) {
        Result<T> result = new Result<>();
        result.setCode("500"); // 致命错误对应500码
        result.setMessage(message);
        result.setData(null);
        result.setSuccess(false);
        result.setType("fatal"); // 区分致命错误的type
        return result;
    }

}
