package com.hyx.authority.controller;

import com.hyx.authority.service.UserService;
import com.hyx.common.entities.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:35
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/getUserList")
    @ApiOperation(value = "获取用户信息", notes = "备注")
    CommonResult getUserList(){
        return userService.getUserList();
    }
}
