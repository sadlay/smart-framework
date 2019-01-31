package com.lay.javaweb.chapter3.controller;

import com.lay.javaweb.chapter3.service.HelloService;
import com.lay.smartframework.annotation.Action;
import com.lay.smartframework.annotation.Controller;
import com.lay.smartframework.annotation.Inject;
import com.lay.smartframework.annotation.Service;
import com.lay.smartframework.bean.Data;
import com.lay.smartframework.bean.Param;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 23:56 2019/1/30
 * @Modified By:IntelliJ IDEA
 */
@Controller
public class HelloController {
    @Inject
    private HelloService helloService;

    @Action("get:/hello")
    public Data hello(Param param){
        Data data=new Data(helloService.sayHello());
        return data;
    }
}
