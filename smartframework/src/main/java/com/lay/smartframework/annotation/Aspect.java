package com.lay.smartframework.annotation;

import java.lang.annotation.*;

/**
 * @Description: 切面注解
 * @Author: lay
 * @Date: Created in 15:28 2019/2/8
 * @Modified By:IntelliJ IDEA
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    //注解
    Class<? extends Annotation> value();
}
