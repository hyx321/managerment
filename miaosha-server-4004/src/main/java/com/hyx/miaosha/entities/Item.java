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
 * 商品表
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Item对象", description="商品表")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名")
    private String name;

    @ApiModelProperty(value = "商品编号")
    private String code;

    @ApiModelProperty(value = "库存")
    private Long stock;

    @ApiModelProperty(value = "采购时间")
    private Date purchaseTime;

    @ApiModelProperty(value = "是否有效（1=是；0=否）")
    private Integer isActive;

    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
