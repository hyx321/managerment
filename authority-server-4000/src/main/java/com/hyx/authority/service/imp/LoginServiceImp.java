package com.hyx.authority.service.imp;

import com.hyx.authority.jwt.JwtToken;
import com.hyx.authority.service.LoginService;
import com.hyx.authority.utils.JwtTokenUtils;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpUser;
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
    public CommonResult checkUser(SpUser user) {

        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        JwtToken jwtToken = new JwtToken(jwtTokenUtils.generateToken(user));
        Subject subject = SecurityUtils.getSubject();
        subject.login(jwtToken);

        if(subject.isAuthenticated()){
            return new CommonResult<>(200,"欢迎你："+user.getUsername(),jwtToken);
        }else{
            return new CommonResult<>(201,"账号或密码错误","no");
        }
    }
}
