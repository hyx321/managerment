package com.hyx.user.controller;


import com.hyx.common.entities.CommonResult;
import com.hyx.user.service.SpPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/sp-permission")
@Api(tags = "权限相关操作",value = "权限相关操作")
public class SpPermissionController {

    @Resource
    SpPermissionService spPermissionService;

    @ApiOperation(value = "获取权限列表列表", notes = "备注")
    @GetMapping(value = "/getPermissionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    public CommonResult getPermissionList(int current, int size){
        return spPermissionService.getPermissionList(current,size);
    }
}
