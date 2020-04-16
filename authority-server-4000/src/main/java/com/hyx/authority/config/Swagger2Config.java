package com.hyx.authority.config;

import com.hyx.common.config.SwaggerCommonConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/16 11:31
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    private SwaggerCommonConfig swaggerCommonConfig =new SwaggerCommonConfig();

    @Value("${moudle.name")
    String packName;

    @Bean
    public Docket createRestApi() {
        String packName = "authority";
        return swaggerCommonConfig.createRestApi(packName);
    }

}
