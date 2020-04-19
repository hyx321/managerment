package com.hyx.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.hyx.common.entities.Menu;
import com.hyx.common.utils.RedisUtils;
import com.hyx.goods.mapper.MenuDao;
import com.hyx.goods.service.MenuService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/14 19:10
 */
@Service
public class MenuServiceImp implements MenuService {

    @Resource
    MenuDao menuDao;

    @Resource
    RedisUtils redisUtils;

    @Resource
    RedisTemplate<String,Menu> redisTemplate;

    @Override
    public List<Menu> getMenu() {

        String tempMenu = JSONArray.toJSONString(redisUtils.lGet("com;hyx:menu",0,-1));
        List<Menu> temp = JSONArray.parseArray(tempMenu,Menu.class);
        if(temp.size() == 0){
            temp =  menuDao.getMenu();
            ///redisUtils.lSetList("com;hyx:menu",temp);
            redisTemplate.opsForList().leftPushAll("com;hyx:menu",temp);
        }
        return temp;
    }
}