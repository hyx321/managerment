package com.hyx.authority.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.hyx.authority.jwt.JwtToken;
import com.hyx.authority.service.LoginService;
import com.hyx.authority.utils.JwtTokenUtils;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:34
 */

@Service
public class LoginServiceImp implements LoginService {

    @Resource
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public CommonResult CheckUser(User user) {

        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        JwtToken jwtToken = new JwtToken(jwtTokenUtils.generateToken(user));
        Subject subject = SecurityUtils.getSubject();
        subject.login(jwtToken);

//        String temp = JSONObject.toJSONString(redisTemplate.opsForValue().get("login:producer:4000:user:"+user.getName()));
//        User user1 = JSONObject.parseObject(temp,User.class);
        if(subject.isAuthenticated()){
            return new CommonResult<>(200,"欢迎你："+user.getName(),jwtToken);
        }else{
            return new CommonResult<>(201,"账号或密码错误","no");
        }
    }

    @Override
    public User CheckUser_1(String name) {
        return null;
    }
}
