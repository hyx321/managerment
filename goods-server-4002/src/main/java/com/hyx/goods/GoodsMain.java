package com.hyx.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/14 19:02
 */
@ComponentScan("com.hyx")
@SpringBootApplication
public class GoodsMain {
    public static void main(String[] args) {
        SpringApplication.run(GoodsMain.class,args);
    }
}
