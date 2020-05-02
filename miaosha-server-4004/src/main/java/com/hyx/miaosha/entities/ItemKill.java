package com.hyx.miaosha.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 待秒杀商品表
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ItemKill对象", description="待秒杀商品表")
public class ItemKill implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Integer itemId;

    @ApiModelProperty(value = "可被秒杀的总数")
    private Integer total;

    @ApiModelProperty(value = "秒杀开始时间")
    private Date startTime;

    @ApiModelProperty(value = "秒杀结束时间")
    private Date endTime;

    @ApiModelProperty(value = "是否有效（1=是；0=否）")
    private Integer isActive;

    @ApiModelProperty(value = "创建的时间")
    private Date createTime;

    @ApiModelProperty(value = "商品名字")
    private String itemName;

    @ApiModelProperty(value = "是否开始抢")
    private Integer isKill;

}
