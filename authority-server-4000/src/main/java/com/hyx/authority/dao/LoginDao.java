package com.hyx.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyx.common.entities.SpUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:29
 */

@Mapper
public interface LoginDao extends BaseMapper<SpUser> {

    SpUser checkUser(SpUser user);

}
