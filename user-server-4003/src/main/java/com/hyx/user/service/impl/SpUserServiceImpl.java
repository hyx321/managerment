package com.hyx.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyx.user.entity.SpUser;
import com.hyx.user.mapper.SpUserMapper;
import com.hyx.user.service.SpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    @Override
    public List<SpUser> getUsers() {
        Page<SpUser> page = new Page<>(1,5);
        spUserMapper.selectPage(page,null);

        List<SpUser> spUsers =  page.getRecords();
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
        return spUsers;
    }
}
