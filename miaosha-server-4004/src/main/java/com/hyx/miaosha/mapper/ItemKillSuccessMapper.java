package com.hyx.miaosha.mapper;

import com.hyx.miaosha.entities.ItemKillSuccess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 秒杀成功订单表 Mapper 接口
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Mapper
public interface ItemKillSuccessMapper extends BaseMapper<ItemKillSuccess> {

    int updateOrderStatus(@Param("code") String code);
}
