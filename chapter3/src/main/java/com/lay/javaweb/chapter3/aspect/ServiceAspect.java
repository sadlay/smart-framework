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
public class ServiceAspect extends AspectProxy {
    private static final Logger LOGGER= LoggerFactory.getLogger(ServiceAspect.class);

    private long begin;

    @Override
    public void begin() {
        LOGGER.debug("ServiceAspect begin ");
    }

    @Override
    public void end() {
        LOGGER.debug(" ServiceAspect end");
    }

}
