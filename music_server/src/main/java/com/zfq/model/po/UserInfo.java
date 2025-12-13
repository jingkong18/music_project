package com.zfq.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserInfo
 * @Author zfq
 * @Date 2025/12/7
 */
@Data

@TableName(value = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)

    private Long userId;

    @TableField("city")

    private String city;

    @TableField("fans")

    private Long fans;

    @TableField("follow")

    private Long follow;

    @TableField("gender")

    private Long gender;

    @TableField("birthday")

    private Date birthday;

    @TableField("introduce")

    private String introduce;

    @TableField("level")

    private String level;

    @TableField("create_time")

    private String createTime;

    @TableField("update_time")

    private String updateTime;
}
