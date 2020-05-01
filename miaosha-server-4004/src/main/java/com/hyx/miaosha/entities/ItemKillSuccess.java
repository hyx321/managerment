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
 * 秒杀成功订单表
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ItemKillSuccess对象", description="秒杀成功订单表")
public class ItemKillSuccess implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "秒杀成功生成的订单编号")
    @TableId(value = "code")
    private String code;

    @ApiModelProperty(value = "商品id")
    private Integer itemId;

    @ApiModelProperty(value = "秒杀id")
    private Integer killId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "秒杀结果: -1无效  0成功(未付款)  1已付款  2已取消")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
