package com.lay.smartframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description: 编码与解码操作工具类
 * @Author: lay
 * @Date: Created in 0:32 2019/2/1
 * @Modified By:IntelliJ IDEA
 */
public class CodeUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(CodeUtil.class);


    /**
     *
     * @Description: 将url编码
     * @param:
     * @param source
     * @return: java.lang.String
     * @auther: lay
     * @date: 0:37 2019/2/1
     */
    public static String encodeUrl(String source){
        String target;
        try {
            target= URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }
    /**
     *
     * @Description: 将url解码
     * @param:
     * @param source
     * @return: java.lang.String
     * @auther: lay
     * @date: 0:38 2019/2/1
     */
    public static String decodeUrl(String source){
        String target;
        try {
            target= URLDecoder.decode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
