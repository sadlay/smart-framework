package com.lay.smartframework.bean;

/**
 * @Description: 返回数据对象
 * @Author: lay
 * @Date: Created in 23:50 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
public class Data {

    //模型数据
    private Object model;

    public Data(Object model){
        this.model=model;
    }

    public Object getModel() {
        return model;
    }
}
