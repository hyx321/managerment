package com.hyx.authority.controller;

import com.hyx.authority.service.LoginService;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:28
 */

@RestController
@Api(tags = "用户验证",value = "用户验证")
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户账号检测", notes = "备注")
    @ApiImplicitParam(name = "user", value = "用户的实体")
    public CommonResult login(@RequestBody SpUser user){
        return loginService.checkUser(user);
    }

    @PostMapping(value = "/logintest")
    @ApiOperation(value = "用户账号检测测试接口", notes = "备注")
    public CommonResult login(){
        SpUser user = new SpUser();
        user.setUsername("admin");
        user.setPassword("123456");
        return loginService.checkUser(user);
    }

    @PostMapping(value = "/home")
    @ApiOperation(value = "测试认证链接", notes = "备注")
    public CommonResult test(){
        log.info("---->LoginController.test()");
        return new CommonResult<>(200,"测试成功","test");
    }
}
