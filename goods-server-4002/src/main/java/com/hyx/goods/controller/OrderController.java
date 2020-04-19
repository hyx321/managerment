package com.hyx.goods.controller;

import com.hyx.common.entities.CommonResult;
import com.hyx.goods.service.SpGoodsService;
import com.hyx.goods.service.SpOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/17 15:23
 */
@RestController
@RequestMapping("/sp-order")
@Api(tags = "订单相关操作",value = "订单相关操作")
public class OrderController {

    @Resource
    SpOrderService spOrderService;

    @ApiOperation(value = "获取订单信息列表", notes = "备注")
    @GetMapping(value = "/getOrderList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    public CommonResult getOrderList(int current,int size){
        return spOrderService.getOrderList(current,size);
    }
}
