package com.hyx.authority.service;

import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.User;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:32
 */
public interface LoginService {

    CommonResult CheckUser(User user);

    User CheckUser_1(String name);
}
