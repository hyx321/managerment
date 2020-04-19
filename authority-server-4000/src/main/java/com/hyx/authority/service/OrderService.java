package com.hyx.authority.service;

import com.hyx.common.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/17 15:44
 */
@FeignClient(name = "goods-server")
public interface OrderService {

    @GetMapping(value = "sp-order/getOrderList")
    CommonResult getOrderList(@RequestParam("current") int current, @RequestParam("size") int size);
}
