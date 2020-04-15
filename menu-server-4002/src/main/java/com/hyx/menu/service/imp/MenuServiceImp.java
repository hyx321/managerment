package com.hyx.menu.service.imp;

import com.hyx.common.entities.Menu;
import com.hyx.menu.dao.MenuDao;
import com.hyx.menu.service.MenuService;
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

    @Override
    public List<Menu> getMenu() {
        return menuDao.getMenu();
    }
}
