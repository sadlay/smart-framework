package com.lay.smartframework.proxy;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:39 2019/2/4
 * @Modified By:IntelliJ IDEA
 */
public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void after() {
        System.out.println("After");
    }

    private void before() {
        System.out.println("Before");
    }
}
