package com.hyx.authority.utils;

import com.hyx.common.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:49
 *
 * 解析和生成 JwtToken
 */

@RefreshScope
public class JwtTokenUtils {

    @Value("${config.stringKey}")
    private String stringKey;

    private final  String keyBackup = "oD2bB1dB0bI2lD3bQ3aC5cC2fA4sE2gPgE0aC4bC5bF0vM0dC5iD1bE2aI4bK2fHaT0dF5cJ1bJ3wE2uO3cA2nB0aE0dG6bApH2aI1cC1cC1eB0aB2bO2aM0pA0aB3lA";

    public String generateToken(User user){
        if(StringUtil.isNullOrEmpty(stringKey)){
            stringKey = keyBackup;
        }
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
        JwtBuilder builder = Jwts.builder()
                .setIssuer(user.getName())
                .setSubject(user.getPassword())
                .setAudience("you")
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID()+"")
                .signWith(key, SignatureAlgorithm.HS384);
        return builder.compact();
    }

    public User decodeToken(String token){
        if(StringUtil.isNullOrEmpty(stringKey)){
            stringKey = keyBackup;
        }
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        User user = new User();
        user.setName(claims.getIssuer());
        user.setPassword(claims.getSubject());
        return user;
    }
}
