package com.lay.smartframework.bean;

import com.lay.smartframework.util.CastUtil;

import java.util.Map;

/**
 * @Description: 请求参数对象
 * @Author: lay
 * @Date: Created in 23:45 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
    /**
     *
     * @Description: 根据参数名获取long类型参数
     * @param:
     * @param name
     * @return: long
     * @auther: lay
     * @date: 23:47 2019/1/31
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }
    /**
     *
     * @Description: 获取所有字段信息
     * @param:
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: lay
     * @date: 23:46 2019/1/31
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
