package com.zfq.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName UserInfoVO
 * @Author zfq
 * @Date 2025/12/14
 */
@Data
@ApiModel(value = "用户信息VO", description = "返回给前端的用户信息")
public class UserInfoVO {
    
    @ApiModelProperty(name = "id", notes = "用户ID")
    private Long id;

    @ApiModelProperty(name = "nickName", notes = "用户名")
    private String nickName;

    @ApiModelProperty(name = "email", notes = "邮箱")
    private String email;

    @ApiModelProperty(name = "icon", notes = "头像")
    private String icon;

    @ApiModelProperty(name = "status", notes = "用户状态（1.正常 2.禁用）")
    private Long status;

    @ApiModelProperty(name = "createTime", notes = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "lastLoginTime", notes = "最后登录时间")
    private LocalDateTime lastLoginTime;
}