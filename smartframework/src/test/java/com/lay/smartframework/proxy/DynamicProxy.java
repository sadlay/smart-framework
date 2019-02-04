package com.lay.smartframework.proxy;

import org.apache.commons.dbutils.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:45 2019/2/4
 * @Modified By:IntelliJ IDEA
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }

    public <T>T getProxy(){
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public static void main(String [] args){
        Hello hello=new HelloImpl();
        DynamicProxy dynamicProxy=new DynamicProxy(hello);
        //Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), dynamicProxy);
        Hello helloProxy=dynamicProxy.getProxy();
        helloProxy.say("Roes1");
    }
}
