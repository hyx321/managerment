package com.hyx.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/13 17:27
 */
@ComponentScan("com.hyx.common")
@SpringBootApplication
@EnableFeignClients
public class AuthorityMain {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityMain.class,args);
    }
}
