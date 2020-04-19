package com.hyx.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.utils.RedisUtils;
import com.hyx.user.entities.SpPermission;
import com.hyx.user.entities.SpRole;
import com.hyx.user.mapper.SpRoleMapper;
import com.hyx.user.service.SpRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@Service
public class SpRoleServiceImpl extends ServiceImpl<SpRoleMapper, SpRole> implements SpRoleService {

    @Resource
    SpRoleMapper spRoleMapper;

    @Resource
    RedisUtils redisUtils;

    @Resource
    RedisTemplate<String,SpRole> redisTemplate;

    @Override
    public CommonResult getRoleList() {
        String tempList = JSONArray.toJSONString(redisUtils.lGet("com:hyx.user:roleList",0,-1));
        List<SpRole> temp = JSONArray.parseArray(tempList,SpRole.class);
        if(temp.size() == 0){
            temp = spRoleMapper.selectList(null);
            redisTemplate.opsForList().leftPushAll("com:hyx.user:roleList",temp);
        }
        return new CommonResult<>(200,"成功获取权限列表",temp);
    }
}
