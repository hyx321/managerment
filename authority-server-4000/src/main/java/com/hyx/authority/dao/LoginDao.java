package com.hyx.authority.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyx.common.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:29
 */

@Mapper
public interface LoginDao extends BaseMapper<User> {

    User CheckUser(User user);

    User CheckUser_1(@Param("name") String name);
}
