package com.hyx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 18:12
 */
@ComponentScan("com.hyx")
@SpringBootApplication
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class,args);
    }
}
