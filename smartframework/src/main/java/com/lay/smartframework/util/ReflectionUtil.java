package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 反射工具类
 * @Author: lay
 * @Date: Created in 17:09 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ReflectionUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     *
     * @Description: 创建实例
     * @param:
     * @param clz
     * @return: java.lang.Object
     * @auther: lay
     * @date: 17:13 2019/1/25
     */
    public static Object newInstance(Class<?> clz){
        Object instance;
        try {
            instance=clz.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     *
     * @Description: 调用方法
     * @param:
     * @param obj
     * @param method
     * @param args
     * @return: java.lang.Object
     * @auther: lay
     * @date: 17:14 2019/1/25
     */
    public static Object invokeMethod(Object obj, Method method,Object...args){
        Object result;
        try {
            method.setAccessible(true);
            result=method.invoke(obj,args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     *
     * @Description: 设置成员变量的值
     * @param:
     * @param obj
     * @param field
     * @param value
     * @return: void
     * @auther: lay
     * @date: 17:17 2019/1/25
     */
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}
