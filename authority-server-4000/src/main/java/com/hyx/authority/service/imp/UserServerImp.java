package com.hyx.authority.service.imp;

import com.hyx.authority.service.UserService;
import com.hyx.common.entities.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 19:34
 */
@Service
public class UserServerImp implements UserService {
    @Override
    public CommonResult getUserList() {
        return new CommonResult<>(400,"请求超时","请求超时");
    }
}
