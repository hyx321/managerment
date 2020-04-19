package com.hyx.goods.service;

import com.hyx.common.entities.CommonResult;
import com.hyx.goods.entity.SpGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-17
 */
public interface SpGoodsService extends IService<SpGoods> {

    CommonResult getGoodsList(int current,int size);

}
