package com.lay.smartframework.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Description: Json工具类
 * @Author: lay
 * @Date: Created in 0:39 2019/2/1
 * @Modified By:IntelliJ IDEA
 */
public class JsonUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();

    /**
     *
     * @Description: 将pojo转为json
     * @param:
     * @param obj
     * @return: java.lang.String
     * @auther: lay
     * @date: 0:41 2019/2/1
     */
    public static<T> String toJson(T obj){
        String json;
        try {
            json=OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert pojo to json failure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     *
     * @Description: 将json转为pojo
     * @param:
     * @param json
     * @param type
     * @return: T
     * @auther: lay
     * @date: 0:44 2019/2/1
     */
    public static<T> T fromJson(String json,Class<T> type){
        T pojo;
        try {
            pojo=OBJECT_MAPPER.readValue(json,type);
        } catch (Exception e) {
            LOGGER.error("convert json to pojo failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
