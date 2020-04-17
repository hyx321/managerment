package com.hyx.authority.service;

import com.hyx.common.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:12
 */
@FeignClient(name = "goods-server")
public interface MenuService {

    @GetMapping(value = "/getMenuList")
    CommonResult getMenuList();
}
