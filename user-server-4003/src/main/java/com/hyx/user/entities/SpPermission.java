package com.hyx.user.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SpPermission对象", description="权限表")
public class SpPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ps_id", type = IdType.AUTO)
    private Integer psId;

    @ApiModelProperty(value = "权限名称")
    private String psName;

    @ApiModelProperty(value = "父id")
    private Integer psPid;

    @ApiModelProperty(value = "控制器")
    private String psC;

    @ApiModelProperty(value = "操作方法")
    private String psA;

    @ApiModelProperty(value = "权限等级")
    private String psLevel;

    @ApiModelProperty(value = "权限路径")
    private String psPath;
}
