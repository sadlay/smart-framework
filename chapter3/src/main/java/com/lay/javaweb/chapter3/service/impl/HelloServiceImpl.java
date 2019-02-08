package com.lay.javaweb.chapter3.service.impl;

import com.lay.javaweb.chapter3.service.HelloService;
import com.lay.smartframework.annotation.Service;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:52 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        //throw new RuntimeException("say hello error");
        return "hello world";
    }
}
