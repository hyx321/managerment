package com.hyx.authority.service.imp;

import com.hyx.authority.service.GoodsService;
import com.hyx.authority.service.OrderService;
import com.hyx.common.entities.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/17 15:48
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public CommonResult getOrderList(int current, int size) {
        return new CommonResult<>(400,"请求超时","请求超时");
    }
}
