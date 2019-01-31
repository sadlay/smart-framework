package com.lay.smartframework.helper;

import com.lay.smartframework.util.CollectionUtil;
import com.lay.smartframework.util.ReflectionUtil;

import java.util.*;

/**
 * @Description: Bean助手类
 * @Author: lay
 * @Date: Created in 17:20 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class BeanHelper {

    //定义 Bean 映射（用于存放 Bean 类与 Bean 实例的映射关系）
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet=ClassHelper.getBeanClassSet();
        for (Class<?>  beanClass: beanClassSet) {
            Object obj= ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /**
     *
     * @Description: 获取 Bean 映射
     * @param:
     * @return: java.util.Map<java.lang.Class<?>,java.lang.Object>
     * @auther: lay
     * @date: 17:26 2019/1/25
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     *
     * @Description: 获取 Bean 实例
     * @param:
     * @param cls
     * @return: T
     * @auther: lay
     * @date: 17:27 2019/1/25
     */
    public static <T> T getBean(Class<T> cls){
        List<T> beanList = new ArrayList<>();
/*        if(BEAN_MAP.containsKey(cls)){
            cls.equals()
            return (T)BEAN_MAP.get(cls);
        }*/
        for (Map.Entry<Class<?>, Object> classObjectEntry : BEAN_MAP.entrySet()) {
            Class<?> keyClass = classObjectEntry.getKey();
            Object valueObj = classObjectEntry.getValue();
            if(cls.isAssignableFrom(keyClass)){
                beanList.add((T) valueObj);
            }
        }
        if(CollectionUtil.isEmpty(beanList)){
            throw new RuntimeException("can not get bean by class:{}"+cls);
        }
        if(beanList.size()>1){
            throw new RuntimeException("get bean by class:{}"+cls+" has more than one instance");
        }
        return beanList.get(0);



    }
}
