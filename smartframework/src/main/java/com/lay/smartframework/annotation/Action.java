package com.lay.smartframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 方法注解
 * @Author: lay
 * @Date: Created in 16:43 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     *
     * @Description: 请求类型与路径
     * @param:
     * @return: java.lang.String
     * @auther: lay
     * @date: 16:45 2019/1/25
     */
    String value();
}
