package com.hyx.miaosha.rabbitmq;

import com.hyx.miaosha.mapper.ItemKillSuccessMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : xiaolang
 * @date ：Created in 2020/5/2 14:26
 */
@Slf4j
@Service
public class RabbitSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private Environment env;

    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;


    /**
     * 秒杀成功异步发送邮件通知消息
     */
    public void sendKillSuccessEmailMsg(Integer userId){
        log.info("秒杀成功异步发送邮件通知消息-准备发送消息："+userId);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("email.exchange"));
        rabbitTemplate.setRoutingKey(env.getProperty("email.routingkey"));
        rabbitTemplate.convertAndSend(userId);
    }


    /**
     * 秒杀成功后生成抢购订单-发送信息入死信队列，等待着一定时间失效超时未支付的订单
     * @param orderCode
     */
    public void sendKillSuccessOrderExpireMsg(final String orderCode){
        try {
            if (StringUtils.isNotBlank(orderCode)){
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("dead.prod.exchange"));
                rabbitTemplate.setRoutingKey(env.getProperty("dead.prod.routingkey"));
                rabbitTemplate.convertAndSend(orderCode);
            }
        }catch (Exception e){
            log.error("秒杀成功后生成抢购订单-发送信息入死信队列，等待着一定时间失效超时未支付的订单-发生异常，消息为："+ orderCode);
        }
    }

}
