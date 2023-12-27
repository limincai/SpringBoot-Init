package com.mincai.springbootinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author limincai
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.mincai.springbootinit.mapper")
public class SpringbootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInitApplication.class, args);
    }

}
