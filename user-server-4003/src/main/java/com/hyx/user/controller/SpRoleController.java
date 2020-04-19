package com.hyx.user.controller;


import com.hyx.common.entities.CommonResult;
import com.hyx.user.service.SpRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/sp-role")
@Api(tags = "角色相关操作",value = "角色相关操作")
public class SpRoleController {

    @Resource
    SpRoleService spRoleService;

    @ApiOperation(value = "获取角色列表", notes = "备注")
    @GetMapping(value = "/getRoleList")
    public CommonResult getRoleList(){
        return spRoleService.getRoleList();
    }
}
