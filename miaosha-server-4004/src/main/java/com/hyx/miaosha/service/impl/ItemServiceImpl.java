package com.hyx.miaosha.service.impl;

import com.hyx.miaosha.entities.Item;
import com.hyx.miaosha.mapper.ItemMapper;
import com.hyx.miaosha.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
