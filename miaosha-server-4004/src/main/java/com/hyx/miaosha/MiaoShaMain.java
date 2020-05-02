package com.hyx.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/30 13:31
 */
@SpringBootApplication
@ComponentScan("com.hyx")
public class MiaoShaMain {
    public static void main(String[] args) {
        SpringApplication.run(MiaoShaMain.class,args);
    }
}
