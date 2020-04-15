package com.hyx.user.service;

import com.hyx.user.entity.SpUser;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<SpUser>  getUsers();
}
