package com.hyx.authority.service;

import com.hyx.common.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:33
 */
@FeignClient(name = "user-server")
public interface UserService {

    @GetMapping(value = "sp-user/getUserList")
    CommonResult getUserList(@RequestParam("current") int current, @RequestParam("size") int size);

    @GetMapping(value = "sp-permission/getPermissionList")
    CommonResult getPermissionList(@RequestParam("current") int current, @RequestParam("size") int size);

    @GetMapping(value = "sp-role/getRoleList")
    CommonResult getRoleList();
}
