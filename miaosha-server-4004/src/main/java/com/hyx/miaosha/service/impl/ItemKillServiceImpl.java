package com.hyx.miaosha.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyx.common.entities.CommonResult;
import com.hyx.common.entities.SpUser;
import com.hyx.common.utils.RedisUtils;
import com.hyx.miaosha.entities.ItemKill;
import com.hyx.miaosha.entities.ItemKillSuccess;
import com.hyx.miaosha.entities.KillRequest;
import com.hyx.miaosha.mapper.ItemKillMapper;
import com.hyx.miaosha.mapper.ItemKillSuccessMapper;
import com.hyx.miaosha.rabbitmq.RabbitSender;
import com.hyx.miaosha.service.ItemKillService;
import com.hyx.miaosha.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 待秒杀商品表 服务实现类
 * </p>
 *
 * @author xiaolang
 * @since 2020-04-30
 */
@Service
@Slf4j
public class ItemKillServiceImpl extends ServiceImpl<ItemKillMapper, ItemKill> implements ItemKillService {

    private static Logger logger = LoggerFactory.getLogger(ItemKillServiceImpl.class);

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private  ItemKillMapper itemKillMapper;

    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RabbitSender rabbitSender;

    @Override
    public CommonResult getMiaoshaList() {
        List<ItemKill> itemKills = itemKillMapper.getMiaoshaList();
        if(itemKills.size() > 0){
            return new CommonResult<>(200,"获取秒杀列表",itemKills);
        }else{
            return new CommonResult<>(400,"目前没有秒杀列表",null);
        }

    }


    public CommonResult getMiaosha(KillRequest killRequest)throws Exception{

        String lock = "lock";
        RLock rLock = redissonClient.getLock(lock);
        try {
            if (rLock.tryLock(0, 3, TimeUnit.SECONDS)) {

                //todo:查看用户是否抢购成功过一个商品
                if(redisUtils.get("com:hyx:miaosha"+killRequest.getItemId()+ ":"+killRequest.getUserId()) != null){
                    return new CommonResult<>(400, "您已经抢购过该商品了", null);
                }

                //todo：获取商品基本信息，存入缓存中
                String temp = JSONObject.toJSONString(redisUtils.hmget("com:hyx:miaosha:goods:"+killRequest.getItemId()));
                ItemKill itemKill = JSONObject.parseObject(temp,ItemKill.class);

                if(itemKill.getIsKill() == null){
                    itemKill = itemKillMapper.getGoods(killRequest.getItemId());
                    if(itemKill == null){
                        return new CommonResult<>(401, "商品活动已结束", null);
                    }
                    Map<String,Object> map = new HashMap<>();
                    map.put("isKill",itemKill.getIsKill());
                    map.put("total",itemKill.getTotal());
                    map.put("itemId",itemKill.getItemId());
                    map.put("id",itemKill.getId());
                    redisUtils.hmset("com:hyx:miaosha:goods:"+killRequest.getItemId(),map,60);
                    redisUtils.set("com:hyx:miaosha:goods:total:"+killRequest.getItemId(),itemKill.getTotal(),60);
                }

                //todo:查看商品是否下架
                if (itemKill.getIsKill() == 0) {
                    return new CommonResult<>(401, "商品活动已结束", null);
                }
                long total = Integer.parseInt(redisUtils.get("com:hyx:miaosha:goods:total:"+killRequest.getItemId()).toString());
                //todo:查看商品库存是否还有
                if(total <= 0){
                    return new CommonResult<>(402, "商品已被抢光，你慢了一步", null);
                }

                //todo:抢购商品，库存减少，成功则在数据库中增加订单信息
                total = redisUtils.decr("com:hyx:miaosha:goods:total:"+killRequest.getItemId(),1);

                //todo:当缓存中的库存减少到0时，就去修改数据库中的库存数据
                if(total == 0){
                    itemKillMapper.updateGoodsTotal(killRequest.getItemId());
                }
                if (total >= 0) {
                    commonRecordKillSuccessInfo(itemKill, killRequest.getUserId());
                    return new CommonResult<>(200, "抢购成功", null);
                }
            }
        }finally {
            if(rLock.isLocked()){
                if(rLock.isHeldByCurrentThread()){
                    rLock.unlock();
                }
            }
        }
        return new CommonResult<>(402, "因神秘事件而导致你的秒杀失败", null);
    }

    private void commonRecordKillSuccessInfo(ItemKill kill,Integer userId) throws Exception{
        SnowFlake snowFlake=new SnowFlake(2,3);

        //TODO:记录抢购成功后生成的秒杀订单记录
        ItemKillSuccess entity=new ItemKillSuccess();
        String orderNo=String.valueOf(snowFlake.nextId());

        entity.setCode(orderNo);
        entity.setItemId(kill.getItemId());
        entity.setKillId(kill.getId());
        entity.setUserId(userId.toString());
        entity.setStatus(0);
        entity.setCreateTime(DateUtil.parse(DateUtil.now()));

        //TODO:再次检查是否已经抢购成功，并生成订单信息
        if(redisUtils.get("com:hyx:miaosha"+kill.getItemId()+ ":"+userId) == null){
            int res=itemKillSuccessMapper.insert(entity);
            if(res>0){
                redisUtils.set("com:hyx:miaosha"+kill.getItemId()+ ":"+userId,entity,60);
                //TODO:进行异步邮件消息的通知=rabbitmq+mail
                rabbitSender.sendKillSuccessEmailMsg(userId);

                //TODO:入死信队列，用于 “失效” 超过指定的TTL时间时仍然未支付的订单
                rabbitSender.sendKillSuccessOrderExpireMsg(orderNo);
            }
        }
    }
}
