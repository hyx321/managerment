package com.hyx.authority.service;

import com.hyx.common.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:33
 */
@FeignClient(name = "user-server")
public interface UserService {

    @GetMapping(value = "sp-user/getUserList")
    CommonResult getUserList();
}
