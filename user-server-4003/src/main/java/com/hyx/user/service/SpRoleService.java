package com.hyx.user.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hyx.common.entities.CommonResult;
import com.hyx.user.entities.SpRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
public interface SpRoleService extends IService<SpRole> {

    CommonResult getRoleList();
}
