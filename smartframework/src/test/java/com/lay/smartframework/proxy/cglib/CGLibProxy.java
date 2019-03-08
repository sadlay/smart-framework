package com.lay.smartframework.proxy.cglib;

import com.lay.smartframework.proxy.Hello;
import com.lay.smartframework.proxy.HelloImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:58 2019/2/4
 * @Modified By:IntelliJ IDEA
 */
public class CGLibProxy implements MethodInterceptor {
    public  <T>T getProxy(Class cls){
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("Cglib Before");
    }
    private void after(){
        System.out.println("Cglib After");
    }

    public static void main(String[] args){
        CGLibProxy cgLibProxy=new CGLibProxy();
        Hello helloProxy=cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("Rose");
    }
}
