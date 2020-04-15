package com.hyx.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/15 18:12
 */
@SpringBootApplication
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class,args);
    }
}
