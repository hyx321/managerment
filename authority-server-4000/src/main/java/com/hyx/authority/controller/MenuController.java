package com.hyx.authority.controller;

import com.hyx.authority.service.MenuService;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:10
 */

@RestController
@RequestMapping("/menu")
@Api(tags = "菜单信息接口",value = "菜单信息接口")
public class MenuController {

    @Resource
    MenuService menuService;

    @GetMapping(value = "/getMenuList")
    @ApiOperation(value = "获取菜单列表", notes = "备注")
    public CommonResult getMenuList(){
        return  menuService.getMenuList();
    }

}
