package com.hyx.user.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.Menu;
import com.hyx.common.utils.RedisUtils;
import com.hyx.user.entities.SpPermission;
import com.hyx.user.mapper.SpPermissionMapper;
import com.hyx.user.service.SpPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-19
 */
@Service
public class SpPermissionServiceImpl extends ServiceImpl<SpPermissionMapper, SpPermission> implements SpPermissionService {

    @Resource
    SpPermissionMapper spPermissionMapper;

    @Resource
    RedisUtils redisUtils;

    @Resource
    RedisTemplate<String,SpPermission> redisTemplate;

    @Override
    public CommonResult getPermissionList(int current, int size) {
        int start = current * size ;
        int end = start + size - 1;
        String tempList = JSONArray.toJSONString(redisUtils.lGet("com:hyx.user:permissionlist",start,end));
        List<SpPermission> temp = JSONArray.parseArray(tempList,SpPermission.class);
        if(temp.size() == 0){
            temp = spPermissionMapper.getPermissionList();
            redisTemplate.opsForList().leftPushAll("com:hyx.user:permissionlist",temp);
            tempList = JSONArray.toJSONString(redisUtils.lGet("com:hyx.user:permissionlist",start,end));
            temp = JSONArray.parseArray(tempList,SpPermission.class);
        }
        return new CommonResult<>(200,"成功获取权限列表",temp);
    }
}
