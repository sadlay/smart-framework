package com.lay.javaweb.chapter3.aspect;

import com.lay.smartframework.annotation.Aspect;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

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

    @Override
    public void begin() {
        LOGGER.debug("begin");
    }

    @Override
    public void end() {
        LOGGER.debug("end");
    }

    @Override
    public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return false;
    }

    @Override
    public void error(Class<?> cls, Method method, Object[] params, Exception e) {
        LOGGER.debug("error");
    }
}
