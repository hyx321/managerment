package com.hyx.user.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SpManager对象", description="管理员表")
public class SpManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "mg_id", type = IdType.AUTO)
    private Integer mgId;

    @ApiModelProperty(value = "名称")
    private String mgName;

    @ApiModelProperty(value = "密码")
    private String mgPwd;

    @ApiModelProperty(value = "注册时间")
    private Integer mgTime;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    private String mgMobile;

    private String mgEmail;

    @ApiModelProperty(value = "1：表示启用 0:表示禁用")
    private Integer mgState;


}
