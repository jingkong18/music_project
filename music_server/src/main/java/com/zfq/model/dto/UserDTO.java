package com.zfq.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfq.model.po.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName UserDTO
 * @Author zfq
 * @Date 2025/12/7
 */
@Data
@Schema(description = "用户登录表单")
@TableName("tb_user")
public class UserDTO extends User {
    private String code;
}
