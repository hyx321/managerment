package com.hyx.miaosha.controller;


import com.hyx.common.entities.CommonResult;
import com.hyx.miaosha.entities.KillRequest;
import com.hyx.miaosha.service.ItemKillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 待秒杀商品表 前端控制器
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@RestController
@RequestMapping("/item-kill")
@Api(tags = "秒杀相关操作",value = "秒杀相关操作")
public class ItemKillController {
    @Resource
    private ItemKillService itemKillService;

    @GetMapping(value = "/getMaioshaList")
    @ApiOperation(value = "获取秒杀列表", notes = "备注")
    public CommonResult getMenuList(){
        return itemKillService.getMiaoshaList();
    }

    @PostMapping(value = "/getMaiosha")
    @ApiOperation(value = "秒杀商品", notes = "秒杀商品")
    public CommonResult getMaiosha( @RequestBody  KillRequest killRequest) throws Exception{
        return itemKillService.getMiaosha(killRequest);
    }

}
