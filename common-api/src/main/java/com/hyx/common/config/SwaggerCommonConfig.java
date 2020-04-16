package com.hyx.common.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author : xiaolang
 * @date ：Created in 2020/4/16 13:10
 */
public class SwaggerCommonConfig {


    public Docket createRestApi(String packageName) {
        String name = "com.hyx."+packageName+".controller";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(name))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("viboot-swagger2")
                .description("Restful-API-Doc")
                .termsOfServiceUrl("https://www.baidu.com")
                .contact(new Contact("小小浪", "https://127.0.0.1:8080/hello", "huangyouxin123@qq.com"))
                .version("1.0")
                .build();
    }
}
