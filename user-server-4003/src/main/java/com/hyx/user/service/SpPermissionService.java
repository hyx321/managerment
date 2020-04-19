package com.hyx.user.service;

import com.hyx.common.entities.CommonResult;
import com.hyx.user.entities.SpPermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
public interface SpPermissionService extends IService<SpPermission> {

    CommonResult getPermissionList(int current,int size);
}
