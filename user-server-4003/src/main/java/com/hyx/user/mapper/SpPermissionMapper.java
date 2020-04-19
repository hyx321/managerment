package com.hyx.user.mapper;

import com.hyx.user.entities.SpPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@Mapper
public interface SpPermissionMapper extends BaseMapper<SpPermission> {

    List<SpPermission> getPermissionList();
}
