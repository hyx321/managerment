package com.hyx.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpOrder;
import com.hyx.goods.entity.SpGoods;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-17
 */
public interface SpOrderService extends IService<SpOrder> {

    CommonResult getOrderList(int current, int size);
}
