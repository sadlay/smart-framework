package com.lay.smartframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 依赖注入注解
 * @Author: lay
 * @Date: Created in 16:47 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
