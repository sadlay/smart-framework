package com.lay.smartframework.proxy;

/**
 * @Description: 代理接口
 * @Author: lay
 * @Date: Created in 15:31 2019/2/8
 * @Modified By:IntelliJ IDEA
 */
public interface Proxy {

    /**
     *
     * @Description: 执行链式代理
     * @param:
     * @param proxyChain
     * @return: java.lang.Object
     * @auther: lay
     * @date: 15:32 2019/2/8
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
