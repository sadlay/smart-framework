package com.lay.smartframework.helper;

import com.lay.smartframework.annotation.Action;
import com.lay.smartframework.bean.Handler;
import com.lay.smartframework.bean.Request;
import com.lay.smartframework.util.ArrayUtil;
import com.lay.smartframework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 控制器助手类
 * @Author: lay
 * @Date: Created in 0:24 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
public final class ControllerHelper {

    //用于存放请求与处理器的映射关系（简称Action Map）
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static{
        //获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            //遍历这些Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                //获取Controller中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    //遍历这些Controller中的方法
                    for (Method method : methods) {
                        //判断当前方法是否带有Action注解
                        if(method.isAnnotationPresent(Action.class)){
                            //从Action注解中获取URL映射规则
                            Action action=method.getAnnotation(Action.class);
                            String mapping=action.value();
                            //验证url映射规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] arrays = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(arrays)&&arrays.length==2){
                                    //获取请求方法与请求路径
                                    String requestMethod=arrays[0];
                                    String requestPath=arrays[1];
                                    Request request=new Request(requestMethod,requestPath);
                                    Handler handler=new Handler(controllerClass,method);
                                    //初始化Action的map
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    /**
     *
     * @Description: 获取Handler
     * @param:
     * @param requestMethod
     * @param requestPath
     * @return: com.lay.smartframework.bean.Handler
     * @auther: lay
     * @date: 0:39 2019/1/31
     */
    public static Handler getHandler(String requestMethod,String requestPath){
        Request request=new Request(requestMethod,requestPath);
        Handler handler = ACTION_MAP.get(request);
        return handler;
    }
}
