package com.lay.smartframework.proxy;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:39 2019/2/4
 * @Modified By:IntelliJ IDEA
 */
public class HelloImpl  implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello "+name);
        //throw new RuntimeException("exception for invoke method:say(String name)");
    }
}
