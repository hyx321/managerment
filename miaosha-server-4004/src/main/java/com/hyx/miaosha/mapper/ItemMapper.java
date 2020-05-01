package com.hyx.miaosha.mapper;

import com.hyx.miaosha.entities.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
