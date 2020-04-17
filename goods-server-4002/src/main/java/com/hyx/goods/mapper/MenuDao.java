package com.hyx.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyx.common.entities.Menu;
import com.hyx.common.entities.MenuBase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/14 19:19
 */

@Mapper
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> getMenu();

    List<MenuBase> getMenuBase(int id);
}
