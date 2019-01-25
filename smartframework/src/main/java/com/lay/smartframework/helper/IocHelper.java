package com.lay.smartframework.helper;

import com.lay.smartframework.annotation.Inject;
import com.lay.smartframework.util.CollectionUtil;
import com.lay.smartframework.util.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Description: 依赖注入助手类
 * @Author: lay
 * @Date: Created in 17:31 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class IocHelper {
    static {
        //获取所有的 Bean 类与 bean 实例之间的映射关系（简称 Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            //遍历 beanMap
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从 beanMap 中获取 bean类与 bean的实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                //获取 Bean 类中定义的所有成员变量（ bean filed）
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    //是遍历 beanFields
                    for (Field field : beanFields) {
                        //判断变量是否带有 @Inject 注解
                        if(field.isAnnotationPresent(Inject.class)){
                            //在bean map中获取 field对应的实例
                            Class<?> beanTypeClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanTypeClass);
                            if(beanFieldInstance!=null){
                                //通过反射初始化 beanField的值
                                ReflectionUtil.setField(beanInstance,field,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
