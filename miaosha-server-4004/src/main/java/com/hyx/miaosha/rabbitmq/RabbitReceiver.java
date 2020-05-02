package com.hyx.miaosha.rabbitmq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyx.miaosha.entities.ItemKillSuccess;
import com.hyx.miaosha.mapper.ItemKillSuccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/5/2 14:25
 */
@Slf4j
@Service
public class RabbitReceiver {

    @Resource
    private Environment env;

    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;


    /**
     * 秒杀异步邮件通知-接收消息
     */
    @RabbitListener(queues = {"${email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(String  info){
        log.info("秒杀异步邮件通知-接收消息:"+info);
    }

    /**
     * 用户秒杀成功后超时未支付,将订单的状态变成无效订单
     * @param code
     */
    @RabbitListener(queues = {"${dead.prod.queue}"},containerFactory = "singleListenerContainer")
    public void consumeExpireOrder(String code){
        try {
            log.info("用户秒杀成功后超时未支付-监听者-接收消息:"+code);
            itemKillSuccessMapper.updateOrderStatus(code);
        }catch (Exception e){
            log.error("用户秒杀成功后超时未支付-监听者-发生异常：",e.fillInStackTrace());
        }
    }
}