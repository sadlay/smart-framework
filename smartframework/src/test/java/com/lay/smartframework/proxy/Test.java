package com.lay.smartframework.proxy;

import com.lay.smartframework.proxy.origin.HelloProxy;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:41 2019/2/4
 * @Modified By:IntelliJ IDEA
 */
public class Test {
    public static void main(String [] args){
        HelloProxy helloProxy=new HelloProxy();
        helloProxy.say("Jack");
    }
}
