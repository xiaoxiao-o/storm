/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Swagger2Config
 * Author:   whyxs
 * Date:     2019/6/17 14:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/6/17
 * @since 1.0.0
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.whyxs.controller.api")) // 注意修改此处的包名,仅api下的包配置
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口列表 v1.1.0") // 任意，请稍微规范点
                .description("开发测试用") // 任意，请稍微规范点
                .termsOfServiceUrl("http://localhost:8080/manager/swagger-ui.html") // 将“url”换成自己的ip:port
                .contact(new Contact("whyxs","http://whyxs.com","1011157554@qq.com")) // 无所谓（这里是作者信息）
                .version("1.1.0")
                .build();
    }
}
