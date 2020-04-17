package com.hyx.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpUser;
import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-15
 */
public interface SpUserService extends IService<SpUser> {

    CommonResult getUsers(int current, int size);
}
