package com.lay.smartframework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @Description: 数组工具类
 * @Author: lay
 * @Date: Created in 17:58 2019/1/25
 * @Modified By:IntelliJ IDEA
 */
public final class ArrayUtil {

    /**
     *
     * @Description: 判断数组是否非空
     * @param:
     * @param array
     * @return: boolean
     * @auther: lay
     * @date: 17:59 2019/1/25
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }


    /**
     *
     * @Description: 判断数组是否为空
     * @param:
     * @param array
     * @return: boolean
     * @auther: lay
     * @date: 18:00 2019/1/25
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }


}
