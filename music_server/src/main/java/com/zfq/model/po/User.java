package com.zfq.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Author zfq
 * @Date 2025/12/7
 */
@Data

@TableName("tb_user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)

    private Long id;

    @TableField(value = "phone")
    private String phone;


    @TableField(value = "password")
    private String password;


    @TableField(value = "nick_name")
    private String nickName;


    @TableField(value = "email")
    private String email;


    @TableField(value = "status")
    private Long status;


    @TableField(value = "icon")
    private String icon;


    @TableField(value = "create_time")
    private Date createTime;


    @TableField(value = "update_time")
    private Date updateTime;


    @TableField(value = "last_login_time")
    private Date lastLoginTime;
}
