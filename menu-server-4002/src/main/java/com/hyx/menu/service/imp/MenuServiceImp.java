package com.hyx.menu.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyx.common.entities.Menu;
import com.hyx.common.entities.SpUser;
import com.hyx.menu.dao.MenuDao;
import com.hyx.menu.service.MenuService;
import com.hyx.menu.utils.RedisUtils;
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
        String tempMenu = JSONArray.toJSONString(redisUtils.get("com;hyx:menu"));
        List<Menu> temp = JSONArray.parseArray(tempMenu,Menu.class);
        if(temp == null){
            temp =  menuDao.getMenu();
            redisUtils.set("com;hyx:menu",temp);
        }
        return temp;
    }
}
