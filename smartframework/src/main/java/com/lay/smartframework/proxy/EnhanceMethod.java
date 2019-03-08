package com.lay.smartframework.proxy;

import java.lang.reflect.Method;

/**
 * @Description: 增强方法
 * @Author: lay
 * @Date: Created in 18:34 2019/2/8
 * @Modified By:IntelliJ IDEA
 */
public interface EnhanceMethod {

    default public void begin() {
    }

    default public void end() {
    }

    default public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    default public void after(Class<?> cls, Method method, Object[] params, Object result) {
    }

    default public void before(Class<?> cls, Method method, Object[] params) {
    }

    default public void error(Class<?> cls, Method method, Object[] params, Exception e) {
    }


}
