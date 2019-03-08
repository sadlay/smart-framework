package com.lay.smartframework.proxy.aspect.impl;

import com.lay.smartframework.proxy.aspect.Invocation;
import com.lay.smartframework.proxy.aspect.Aspect;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.dbutils.ProxyFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:57 2019/2/7
 * @Modified By:IntelliJ IDEA
 */
public class MyAspect implements Aspect {
    @Override
    public void before() {
        System.out.println("before");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before around");
        Object result = invocation.proceed();
        System.out.println("after around");
        return result;
    }

    @Override
    public void after() {
        System.out.println("after");
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    @Override
    public boolean useAround() {
         return true;
    }
}
