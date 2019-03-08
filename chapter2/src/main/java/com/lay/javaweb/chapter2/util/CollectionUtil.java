package com.lay.javaweb.chapter2.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @Description: 集合工具类
 * @Author: lay
 * @Date: Created in 16:40 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class CollectionUtil {
    //判断集合是否为空
    public static boolean isEmpty(Collection<?> collection){
       return CollectionUtils.isEmpty(collection);
    }

    //判断集合是否非空
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    //判断map是否非空
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    //判断map是否非空
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }
}
