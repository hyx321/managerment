package com.hyx.goods.config;

import com.hyx.common.config.SwaggerCommonConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/16 13:34
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${moudle.name")
    String packName;

    @Bean
    public Docket createRestApi() {
        SwaggerCommonConfig swaggerCommonConfig = new SwaggerCommonConfig();
        return swaggerCommonConfig.createRestApi(packName);
    }
}
