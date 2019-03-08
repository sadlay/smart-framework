package com.lay.smartframework.bean;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Description: 封装Action信息
 * @Author: lay
 * @Date: Created in 0:22 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
public class Handler {
    //Controller类
    private Class<?> controllerClass;

    //Action方法
    private Method method;

    public Handler(Class<?> controllerClass, Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getMethod() {
        return method;
    }
}
