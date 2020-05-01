package com.hyx.miaosha.mapper;

import com.hyx.miaosha.entities.ItemKill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 待秒杀商品表 Mapper 接口
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Mapper
public interface ItemKillMapper extends BaseMapper<ItemKill> {

    List<ItemKill> getMiaoshaList();

    ItemKill getGoods(@Param("itemId") int itemId);

    Integer updateGoodsTotal(@Param("itemId") int itemId);
}
