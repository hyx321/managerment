package com.hyx.common.service;

import com.hyx.common.entities.User;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 10:33
 */

public interface TokenService {

    /**
     * 生成Token
     * @param user
     * @return
     */
    String generateToken(User user);

    /**
     * 解析Token
     * @param token
     * @return
     */
    User decodeToken(String token);
}
