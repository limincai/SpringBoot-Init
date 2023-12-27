package com.mincai.springbootinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author limincai
 * Knief4j Api文档配置
 * 默认访问 <a href="http://localhost:8080/doc.html"/>
 */
@Configuration
@EnableSwagger2WebMvc
@Profile({"dev"})
public class Knife4jConfig {

    @Bean
    public Docket createApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo()).select()
                // Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.mincai.springbootinit.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder().title("SpringBoot-Init").description("通用的 SpringBoot2 后端快速初始化项目").termsOfServiceUrl("https://github.com/limincai/SpringBoot-Init").contact(new Contact("李民才", "https://github.com/limincai", "3206820023@qq.com")).version("1.0").build();
    }
}