package com.hyx.authority.service.imp;

import com.hyx.authority.service.MenuService;
import com.hyx.common.entities.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:14
 */
@Service
public class MenuServiceImp implements MenuService {
    @Override
    public CommonResult getMenuList() {
       return new CommonResult<>(400,"请求超时","请求超时");
    }
}
