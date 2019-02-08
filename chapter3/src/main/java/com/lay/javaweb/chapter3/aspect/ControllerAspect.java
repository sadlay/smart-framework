package com.lay.javaweb.chapter3.aspect;

import com.lay.smartframework.annotation.Aspect;
import com.lay.smartframework.annotation.Controller;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * @Description: 拦截Controller的所有方法
 * @Author: lay
 * @Date: Created in 16:18 2019/2/8
 * @Modified By:IntelliJ IDEA
 */
@Aspect(Service.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        LOGGER.debug("---------Begin----------");
        LOGGER.debug("class:{}",cls.getName());
        LOGGER.debug("method:{}",method.getName());
        begin=System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        LOGGER.debug("time:{}",System.currentTimeMillis()-begin);
        LOGGER.debug("----------End-----------");
    }
}
