package com.lay.javaweb.chapter3.service.impl;

import com.lay.javaweb.chapter3.service.HelloService;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 22:52 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
@Service
public class HelloServiceImpl implements HelloService {
    private static final Logger LOGGER= LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String sayHello() {
        //throw new RuntimeException("say hello error");
        LOGGER.info("HelloServiceImpl Session name {}",String.valueOf(SessionUtil.getSession().getAttribute("name")));
        LOGGER.info("HelloServiceImpl Session name2 {}",String.valueOf(SessionUtil.getSession().getAttribute("name2")));
        return "hello world";
    }
}
