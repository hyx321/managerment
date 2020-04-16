package com.hyx.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyx.user.entity.SpUser;
import com.hyx.user.mapper.SpUserMapper;
import com.hyx.user.service.SpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyx.user.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-15
 */
@Service
public class SpUserServiceImpl extends ServiceImpl<SpUserMapper, SpUser> implements SpUserService {

    @Resource
    SpUserMapper spUserMapper;
    @Resource
    RedisUtils redisUtils;

    @Override
    public List<SpUser> getUsers() {

        String tempMenu = JSONArray.toJSONString(redisUtils.get("com;hyx:user"));
        List<SpUser> temp = JSONArray.parseArray(tempMenu,SpUser.class);

        if(temp == null){
            Page<SpUser> page = new Page<>(1,5);
            spUserMapper.selectPage(page,null);
            temp = page.getRecords();
            page.getTotal();
            redisUtils.set("com;hyx:user",temp);
        }
        return temp;
    }
}
