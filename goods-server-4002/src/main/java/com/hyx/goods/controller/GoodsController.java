package com.hyx.goods.controller;

import com.hyx.common.entities.CommonResult;
import com.hyx.goods.service.SpGoodsService;
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
@RequestMapping("/sp-goods")
public class GoodsController {

    @Resource
    SpGoodsService spGoodsService;

    @ApiOperation(value = "获取商品信息列表", notes = "备注")
    @GetMapping(value = "/getGoodsList")
    public CommonResult getGoodsList(int current,int size){
        return spGoodsService.getGoodsList(current,size);
    }
}
