package com.lay.javaweb.chapter3;

import com.lay.smartframework.HelperLoader;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.helper.BeanHelper;

import java.util.Map;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 23:09 2019/1/30
 * @Modified By:IntelliJ IDEA
 */
@Service
public class Test {

    public static void main(String[] args){
        HelperLoader.init();
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        System.out.println(beanMap);
    }
}
