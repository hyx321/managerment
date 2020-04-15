package com.hyx.user.controller;


import com.hyx.common.entities.CommonResult;
import com.hyx.user.service.SpUserService;
import org.springframework.web.bind.annotation.GetMapping;
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
public class SpUserController {

    @Resource
    SpUserService spUserService;

    @GetMapping(value = "/getUserList")
    public CommonResult getUserList(){
        return new CommonResult<>(200,"2",spUserService.getUsers());
    }
}
