package com.hyx.authority.controller;

import com.hyx.authority.service.UserService;
import com.hyx.common.entities.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:35
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息接口",value = "用户信息接口")
public class UserController {

    @Resource
    private UserService userService;

    @RequiresPermissions(value = "select")
    @GetMapping(value = "/getUserList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    CommonResult getUserList(@RequestParam("current") int current,@RequestParam("size")int size){
        return userService.getUserList(current,size);
    }

    @ApiOperation(value = "获取权限列表列表", notes = "备注")
    @GetMapping(value = "/getPermissionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",dataType = "int",value = "当前页码"),
            @ApiImplicitParam(name = "size",dataType = "int",value = "获取的信息数")})
    public CommonResult getPermissionList(int current, int size){
        return userService.getPermissionList(current,size);
    }

    @ApiOperation(value = "获取角色列表", notes = "备注")
    @GetMapping(value = "/getRoleList")
    public CommonResult getUserList(){
        return userService.getRoleList();
    }
}
