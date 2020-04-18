package com.hyx.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.hyx.common.entities.Menu;
import com.hyx.common.utils.RedisUtils;
import com.hyx.goods.mapper.MenuDao;
import com.hyx.goods.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public List<Menu> getMenu() {
        String tempMenu = JSONArray.toJSONString(redisUtils.get("com;hyx:goods"));
        List<Menu> temp = JSONArray.parseArray(tempMenu,Menu.class);
        if(temp == null){
            temp =  menuDao.getMenu();
            redisUtils.set("com;hyx:goods",temp);
        }
        return temp;
    }
}
