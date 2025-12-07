package com.zfq.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Author zfq
 * @Date 2025/12/7
 */
@Data
@Schema(description = "用户信息表")
@TableName("tb_user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "手机号")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;

    @Schema(description = "昵称")
    @TableField(value = "nick_name")
    private String nickName;

    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "用户状态")
    @TableField(value = "status")
    private Long status;

    @Schema(description = "头像")
    @TableField(value = "icon")
    private String icon;

    @Schema(description = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time")
    private Date updateTime;

    @Schema(description = "最后登录时间")
    @TableField(value = "last_login_time")
    private Date lastLoginTime;
}
