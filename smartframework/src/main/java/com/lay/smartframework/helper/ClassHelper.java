package com.lay.smartframework.helper;

import com.lay.smartframework.annotation.Controller;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 类操作助手类
 * @Author: lay
 * @Date: Created in 14:47 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ClassHelper {

    //定义类集合（用于存放锁加载的类）
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage=ConfigHelper.getAppBasePackage();
        CLASS_SET= ClassUtil.getClassSet(basePackage);
    }

    /**
     *
     * @Description: 获取应用包下所有类
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 16:52 2019/1/25
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    /**
     *
     * @Description: 获取应用包下所有Service类
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:03 2019/1/25
     */
    public static Set<Class<?>> getServiceClassSet(){
        return getClassSet(Service.class);
    }


    /**
     *
     * @Description: 获取应用包下所有Controller类
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:04 2019/1/25
     */
    public static Set<Class<?>> getControllerClassSet(){
        return getClassSet(Controller.class);
    }

    /**
     *
     * @Description: 获取应用包下所有Bean类（包括service,controller）
     * @param:
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:07 2019/1/25
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet=new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }
    /**
     *
     * @Description: 根据注解类型获取应用包下相应类
     * @param:
     * @param clz
     * @return: java.util.Set<java.lang.Class<?>>
     * @auther: lay
     * @date: 17:01 2019/1/25
     */
    public static Set<Class<?>> getClassSet(Class<? extends Annotation> clz){
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for (Class<?> c : CLASS_SET) {
            if(c.isAnnotationPresent(clz)){
                classSet.add(c);
            }
        }
        return classSet;
    }

}
