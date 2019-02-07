package com.lay.smartframework.proxy.aspect;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:48 2019/2/7
 * @Modified By:IntelliJ IDEA
 */
public interface Aspect {
    void before();
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
    void after();
    void afterReturning();
    void afterThrowing();
    boolean useAround();
}
