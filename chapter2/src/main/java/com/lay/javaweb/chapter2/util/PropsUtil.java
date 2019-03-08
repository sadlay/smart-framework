package com.lay.javaweb.chapter2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Description:属性文件工具类
 * @Author: lay
 * @Date: Created in 16:01 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class PropsUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(PropsUtil.class);

    //加载属性文件
    public static Properties loadProps(String fileName){
        Properties props=null;
        InputStream is=null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            //is = PropsUtil.class.getClassLoader().getResourceAsStream(fileName);
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            if(is==null){
                throw new FileNotFoundException(fileName+" file is not found");
            }
            props=new Properties();
            props.load(is);
        } catch (java.io.IOException e) {
            LOGGER.error("load properties file failure",e);
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure",e);
                }
            }
        }
        return props;
    }
    
    //获取字符型属性
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }


    //获取字符型属性（可指定默认值）
    private static String getString(Properties props, String key, String defaultValue) {
        String value=defaultValue;
        if(props.contains(key)){
            value=props.getProperty(key);
        }
        return value;
    }

    //获取数值型属性
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    //获取数值型属性（可指定默认值）
    private static int getInt(Properties props, String key, int defaultValue) {
        int value=defaultValue;
        if(props.contains(key)){
            value= Integer.parseInt(props.getProperty(key));
        }
        return value;
    }

    //获取布尔型属性
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }

    //获取布尔型属性 (可指定默认值)
    private static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value=defaultValue;
        if(props.contains(key)){
            value= Boolean.parseBoolean(props.getProperty(key));
        }
        return value;
    }
}
