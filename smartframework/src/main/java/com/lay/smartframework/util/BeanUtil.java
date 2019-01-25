package com.lay.smartframework.util;

import com.lay.javaweb.chapter2.util.support.StrKit;
import com.lay.javaweb.chapter2.util.support.exception.ToolBoxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:26 2019/1/24
 * @Modified By:IntelliJ IDEA
 */
public class BeanUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    public static BeanInfo getBeanInfo(Class<?> c) {
        try {
            return Introspector.getBeanInfo(c);
        } catch (IntrospectionException e) {
            LOGGER.error("Introspection Class failure ", e);
            throw new RuntimeException(e);
        }
    }

    public static MethodDescriptor[] getMethodDescriptors(Class<?> c) {
        return getBeanInfo(c).getMethodDescriptors();
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> c) {
        PropertyDescriptor[] propertyDescriptors = getBeanInfo(c).getPropertyDescriptors();
        return propertyDescriptors;
    }


    /**
     * 对象转Map
     *
     * @param bean              bean对象
     * @return Map
     */
    public static <T> Map<String, Object> beanToMapWithNull(T bean) {
        boolean isToUnderlineCase=false;
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(bean.getClass());
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (false == key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    map.put(isToUnderlineCase ? StrKit.toUnderlineCase(key) : key, value);
                }
            }
        } catch (Exception e) {
            throw new ToolBoxException(e);
        }
        return map;
    }

}
