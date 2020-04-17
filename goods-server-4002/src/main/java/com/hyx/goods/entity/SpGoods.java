package com.hyx.goods.entity;

import java.math.BigDecimal;
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
 * 商品表
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SpGoods对象", description="商品表")
public class SpGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "商品重量")
    private Integer goodsWeight;

    @ApiModelProperty(value = "类型id")
    private Integer catId;

    @ApiModelProperty(value = "商品详情介绍")
    private String goodsIntroduce;

    @ApiModelProperty(value = "图片logo大图")
    private String goodsBigLogo;

    @ApiModelProperty(value = "图片logo小图")
    private String goodsSmallLogo;

    @ApiModelProperty(value = "0:正常  1:删除")
    private String isDel;

    @ApiModelProperty(value = "添加商品时间")
    private Integer addTime;

    @ApiModelProperty(value = "修改商品时间")
    private Integer updTime;

    @ApiModelProperty(value = "软删除标志字段")
    private Integer deleteTime;

    @ApiModelProperty(value = "一级分类id")
    private Integer catOneId;

    @ApiModelProperty(value = "二级分类id")
    private Integer catTwoId;

    @ApiModelProperty(value = "三级分类id")
    private Integer catThreeId;

    @ApiModelProperty(value = "热卖数量")
    private Integer hotMumber;

    @ApiModelProperty(value = "是否促销")
    private Integer isPromote;

    @ApiModelProperty(value = "商品状态 0: 未通过 1: 审核中 2: 已审核")
    private Integer goodsState;


}
