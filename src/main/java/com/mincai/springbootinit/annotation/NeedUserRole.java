package com.mincai.springbootinit.annotation;

import cn.hutool.core.annotation.Alias;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author limincai
 * 用户接口所需权限注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedUserRole {

    /**
     * 必须拥有的权限
     */
    String value() default "";
}
