package com.hyx.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpUser;
import com.hyx.user.mapper.SpUserMapper;
import com.hyx.user.service.SpUserService;
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
    public CommonResult getUsers(int current, int size) {

        Page<SpUser> page = new Page<>(current,size);
        spUserMapper.selectPage(page,null);
        List<SpUser> temp = page.getRecords();
        page.getTotal();
        if(temp != null){
            return new CommonResult<>(200,"用户列表信息获取成功",temp);
        }else{
            return new CommonResult<>(404,"用户列表信息获取失败",temp);
        }

    }
}
