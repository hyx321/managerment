package com.hyx.authority.controller;

import com.hyx.authority.service.LoginService;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:28
 */

@RestController
@Api(tags = "1.0.0-SNAPSHOT",value = "用户登录认证")
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户账号检测", notes = "备注")
    @ApiImplicitParam(name = "user", value = "用户的实体")
    public CommonResult Login(@RequestBody User user){
        log.info(user.toString());
        return loginService.CheckUser(user);
    }

    @PostMapping(value = "/logintest")
    @ApiOperation(value = "用户账号检测", notes = "备注")
    public CommonResult LoginTest(){
        User userBaseInfo = new User();
        userBaseInfo.setName("hyx");
        userBaseInfo.setPassword("123");
        return loginService.CheckUser(userBaseInfo);
    }

    @GetMapping(value = "/home")
    @ApiOperation(value = "测试是否能通过", notes = "备注")
    public CommonResult test(){
        log.info("---->LoginController.test()");
        return new CommonResult<>(200,"测试成功","test");
    }
}
