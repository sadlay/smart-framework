package com.lay.smartframework.proxy.aspect.impl;

import com.lay.smartframework.proxy.aspect.Invocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:53 2019/2/7
 * @Modified By:IntelliJ IDEA
 */
public class MyInvocation implements Invocation {
    private Object target;
    private Method method;
    private Object[] params;

    public MyInvocation(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    @Override
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,params);
    }
}
