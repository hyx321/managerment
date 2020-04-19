package com.hyx.authority.controller;

import com.hyx.authority.service.GoodsService;
import com.hyx.authority.service.OrderService;
import com.hyx.common.entities.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/17 15:50
 */

@RestController
@Api(tags = "商品相关操作",value = "商品相关操作")
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;

    @Resource
    OrderService orderService;

    @GetMapping(value = "/getGoodsList")
    @ApiOperation(value = "获取商品信息列表", notes = "获取商品信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    public CommonResult getGoodsList(@RequestParam("current") int current, @RequestParam("size")int size){
        return goodsService.getGoodsList(current,size);
    }

    @GetMapping(value = "/getOrderList")
    @ApiOperation(value = "获取订单信息列表", notes = "获取订单信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currenr",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    public CommonResult getOrderList(@RequestParam("current") int current, @RequestParam("size")int size){
        return orderService.getOrderList(current,size);
    }
}
