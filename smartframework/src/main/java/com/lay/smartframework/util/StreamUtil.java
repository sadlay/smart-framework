package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description: 流操作工具类
 * @Author: lay
 * @Date: Created in 0:27 2019/2/1
 * @Modified By:IntelliJ IDEA
 */
public class StreamUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(StreamUtil.class);

    /**
     *
     * @Description: 从输入流中获取字符串
     * @param:
     * @param is
     * @return: java.lang.String
     * @auther: lay
     * @date: 0:28 2019/2/1
     */
    public static String getString(InputStream is){
        StringBuilder sb=new StringBuilder();
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("get String failure",e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
