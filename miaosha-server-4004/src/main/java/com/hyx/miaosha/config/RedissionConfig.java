package com.hyx.miaosha.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/30 19:49
 */
@Configuration
public class RedissionConfig {

    @Value("${spring.redis.host}")
    String ip;
    @Value("${spring.redis.port}")
    String port;

    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer()
                .setAddress("redis://"+ip+":"+port);
        return Redisson.create(config);
    }
}
