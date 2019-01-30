package com.lay.javaweb.chapter3;

import com.lay.smartframework.HelperLoader;
import com.lay.smartframework.annotation.Controller;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.bean.Handler;
import com.lay.smartframework.helper.BeanHelper;
import com.lay.smartframework.helper.ControllerHelper;
import com.lay.smartframework.util.ReflectionUtil;

import java.util.Map;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 23:09 2019/1/30
 * @Modified By:IntelliJ IDEA
 */
public class Test {

    public static void main(String[] args){
        HelperLoader.init();
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        Handler helloHandler = ControllerHelper.getHandler("get", "/hello");
        Object o = ReflectionUtil.invokeMethod(BeanHelper.getBean(helloHandler.getControllerClass()), helloHandler.getMethod());
        System.out.println(o);
        System.out.println(beanMap);
    }
}
