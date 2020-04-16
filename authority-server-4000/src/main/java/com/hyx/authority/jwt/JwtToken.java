package com.hyx.authority.jwt;

import com.hyx.authority.utils.JwtTokenUtils;
import com.hyx.common.entities.SpUser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:35
 * 自定义一个JWTToken
 * 根据获取到的用户名和密码换取的token
 */

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        return jwtTokenUtils.parseToken(token).getPassword();
    }
}
