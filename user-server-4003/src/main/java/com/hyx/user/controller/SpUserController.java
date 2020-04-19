package com.hyx.user.controller;


import com.hyx.common.entities.CommonResult;
import com.hyx.user.service.SpUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/sp-user")
@Api(tags = "用户相关操作",value = "用户相关操作")
public class SpUserController {

    @Resource
    SpUserService spUserService;

    @ApiOperation(value = "获取用户列表", notes = "备注")
    @GetMapping(value = "/getUserList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    public CommonResult getUserList(int current,int size){
        return spUserService.getUsers(current,size);
    }
}
