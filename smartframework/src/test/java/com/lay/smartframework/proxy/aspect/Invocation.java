package com.lay.smartframework.proxy.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:46 2019/2/7
 * @Modified By:IntelliJ IDEA
 */
public interface Invocation {
    public Object proceed() throws InvocationTargetException, IllegalAccessException;
}
