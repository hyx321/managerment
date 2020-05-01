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
import com.hyx.miaosha.service.ItemKillService;
import com.hyx.miaosha.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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

        String lock = killRequest.getItemId()+ ":"+killRequest.getUserId();
        lock = "lock";
        RLock rLock = redissonClient.getLock(lock);
        logger.info("得到锁："+lock);
        try {
            if (rLock.tryLock(0, 3, TimeUnit.SECONDS)) {
                logger.info("重新获取锁："+lock);
                QueryWrapper<ItemKillSuccess> queryWrapperq = new QueryWrapper<>();
                queryWrapperq.eq("user_id", killRequest.getUserId());
                queryWrapperq.eq("item_id", killRequest.getItemId());

                //todo:查看用户是否抢购成功过一个商品
                if (itemKillSuccessMapper.selectOne(queryWrapperq) != null) {
                    return new CommonResult<>(400, "您已经抢购过该商品了", null);
                }
                ItemKill itemKill = itemKillMapper.getGoods(killRequest.getItemId());

                //todo:查看商品是否下架
                if (itemKill == null || itemKill.getIsKill() == 0) {
                    return new CommonResult<>(401, "商品活动已结束", null);
                }

                //todo:查看商品库存是否还有
                if (itemKill.getTotal() <= 0) {
                    return new CommonResult<>(402, "商品已被抢光，你慢了一步", null);
                }
                //todo:抢购商品，库存减少，成功则在数据库中增加订单信息
                int res = itemKillMapper.updateGoodsTotal(killRequest.getItemId());
                if (res > 0) {
                    commonRecordKillSuccessInfo(itemKill, killRequest.getUserId());
                    return new CommonResult<>(200, "抢购成功", null);
                }
            }
        }finally {
            if(rLock.isLocked()){
                if(rLock.isHeldByCurrentThread()){
                    rLock.unlock();
                    logger.info("释放锁："+lock);
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

        QueryWrapper<ItemKillSuccess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id",kill.getItemId());
        queryWrapper.eq("user_id",userId);

        //TODO:再次检查是否已经抢购成功，并生成订单信息
        if (itemKillSuccessMapper.selectCount(queryWrapper) <= 0){
            int res=itemKillSuccessMapper.insert(entity);

//            if (res>0){
//                //TODO:进行异步邮件消息的通知=rabbitmq+mail
//                rabbitSenderService.sendKillSuccessEmailMsg(orderNo);
//
//                //TODO:入死信队列，用于 “失效” 超过指定的TTL时间时仍然未支付的订单
//                rabbitSenderService.sendKillSuccessOrderExpireMsg(orderNo);
//            }
        }
    }
}
