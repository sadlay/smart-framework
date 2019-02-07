package com.lay.smartframework.proxy.jdk;

import com.lay.smartframework.proxy.Hello;
import com.lay.smartframework.proxy.HelloImpl;
import com.lay.smartframework.proxy.aspect.Aspect;
import com.lay.smartframework.proxy.aspect.impl.MyAspect;
import com.lay.smartframework.proxy.aspect.impl.MyInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
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
    private Aspect aspect;

    public DynamicProxy(Object target, Aspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exceptionTag=false;
        Object result=null;
        aspect.before();
        try {
            if(aspect.useAround()) {
                result = aspect.around(new MyInvocation(target,method,args));
            }else{
                result=method.invoke(target, args);
            }
        } catch (Exception e) {
            exceptionTag=true;
            e.printStackTrace();
        }
        aspect.after();
        if(exceptionTag){
            aspect.afterThrowing();
        }else{
            aspect.afterReturning();
            return result;
        }
        return null;
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
        DynamicProxy dynamicProxy=new DynamicProxy(hello, new MyAspect());
        //Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), dynamicProxy);
        Hello helloProxy=dynamicProxy.getProxy();
        helloProxy.say("Roes1");
    }
}
