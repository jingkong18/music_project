package com.zfq.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfq.model.po.User;

import lombok.Data;

/**
 * @ClassName UserDTO
 * @Author zfq
 * @Date 2025/12/7
 */
@Data
@TableName("tb_user")
public class UserDTO extends User {
    private String code;
    private String account;
}
