package com.hyx.goods.controller;

import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.Menu;
import com.hyx.goods.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/14 19:03
 */
@RestController
@Api(tags = "菜单显示操作",value = "菜单显示操作")
public class MenuController {

    @Resource
    MenuService menuService;

    @GetMapping(value = "/getMenuList")
    @ApiOperation(value = "获取菜单列表", notes = "备注")
    public CommonResult getMenuList(){
        List<Menu> menus = menuService.getMenu();
        return new CommonResult<>(200,"成功获取菜单列表",menus);
    }
}
