package com.lay.javaweb.chapter2.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 字符串工具类
 * @Author: lay
 * @Date: Created in 16:35 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class StringUtil {


    //判断字符串是否为空
    public static boolean isEmpty(String str){
        if(str!=null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    //判断字符串是否非空
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
