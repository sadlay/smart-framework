package com.lay.smartframework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Description: 代理管理器
 * @Author: lay
 * @Date: Created in 15:51 2019/2/8
 * @Modified By:IntelliJ IDEA
 */
public class ProxyManager {
    public static<T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, (MethodInterceptor) (o, method, objects, methodProxy) -> new ProxyChain(targetClass,o,method,methodProxy,objects,proxyList).doProxyChain());
    }
}
