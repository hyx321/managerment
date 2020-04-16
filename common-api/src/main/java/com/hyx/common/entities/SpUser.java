package com.hyx.common.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpUser implements Serializable {
    @ApiModelProperty(value = "自增id")
    private Integer userId;

    @ApiModelProperty(value = "登录名")
    private String username;

    @ApiModelProperty(value = "qq官方唯一编号信息")
    private String qqOpenId;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "新用户注册邮件激活唯一校验码")
    private String userEmailCode;

    @ApiModelProperty(value = "新用户是否已经通过邮箱激活帐号")
    private String isActive;

    @ApiModelProperty(value = "性别")
    private String userSex;

    @ApiModelProperty(value = "qq")
    private String userQq;

    @ApiModelProperty(value = "手机")
    private String userTel;

    @ApiModelProperty(value = "学历")
    private String userXueli;

    @ApiModelProperty(value = "爱好")
    private String userHobby;

    @ApiModelProperty(value = "简介")
    private String userIntroduce;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    @ApiModelProperty(value = "修改时间")
    private Integer updateTime;

    @ApiModelProperty(value = "盐")
    private String salt;

    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
}
