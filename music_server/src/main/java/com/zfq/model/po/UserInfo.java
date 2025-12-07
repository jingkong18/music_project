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
 * @ClassName UserInfo
 * @Author zfq
 * @Date 2025/12/7
 */
@Data
@Schema(description = "用户信息表")
@TableName(value = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @Schema(description = "用户id")
    private Long userId;

    @TableField("city")
    @Schema(description = "城市")
    private String city;

    @TableField("fans")
    @Schema(description = "粉丝")
    private Long fans;

    @TableField("follow")
    @Schema(description = "关注")
    private Long follow;

    @TableField("gender")
    @Schema(description = "性别")
    private Long gender;

    @TableField("birthday")
    @Schema(description = "生日")
    private Date birthday;

    @TableField("introduce")
    @Schema(description = "个性签名")
    private String introduce;

    @TableField("level")
    @Schema(description="用户等级")
    private String level;

    @TableField("create_time")
    @Schema(description = "创建时间")
    private String createTime;

    @TableField("update_time")
    @Schema(description = "更新时间")
    private String updateTime;
}
