package com.zfq.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zfq.db.annotation.Column;
import com.zfq.db.annotation.Id;
import com.zfq.db.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import java.time.LocalDateTime;
/**
 * @ClassName User
 * @Author zfq
 * @Date 2025/12/7
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户登录表", description = "用户登录表信息")
@Table(name = "tb_user")
public class User {
    @Id(type = IdType.AUTO)
    @ApiModelProperty(name = "id", notes = "主键")
    @Column(name = "id", jdbcType = JdbcType.BIGINT)
    private Long id;

    @ApiModelProperty(name = "phone", notes = "手机号")
    @Column(name = "phone", jdbcType = JdbcType.VARCHAR)
    private String phone;

    @ApiModelProperty(name = "password", notes = "密码")
    @Column(name = "password", jdbcType = JdbcType.VARCHAR)
    private String password;

    @ApiModelProperty(name = "nickName", notes = "用户名")
    @Column(name = "nick_name", jdbcType = JdbcType.VARCHAR)
    private String nickName;

    @ApiModelProperty(name = "email", notes = "邮箱")
    @Column(name = "email", jdbcType = JdbcType.VARCHAR)
    private String email;

    @ApiModelProperty(name = "status", notes = "用户状态（1.正常 2.禁用）")
    @Column(name = "status", jdbcType = JdbcType.BIGINT)
    private Long status;

    @ApiModelProperty(name = "icon", notes = "头像")
    @Column(name = "icon", jdbcType = JdbcType.VARCHAR)
    private String icon;

    @ApiModelProperty(name = "createTime", notes = "创建时间")
    @Column(name = "create_time", jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime createTime;

    @ApiModelProperty(name = "updateTime", notes = "更新时间")
    @Column(name = "update_time", jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime updateTime;

    @ApiModelProperty(name = "lastLoginTime", notes = "最后登录时间")
    @Column(name = "last_login_time", jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime lastLoginTime;

}