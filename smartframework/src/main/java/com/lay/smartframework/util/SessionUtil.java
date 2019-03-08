package com.lay.smartframework.util;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 9:58 2019/2/18
 * @Modified By:IntelliJ IDEA
 */
public final class SessionUtil {
    private static ThreadLocal<HttpSession> sessionContainer=new ThreadLocal<>();

    public static HttpSession getSession(){
        return  sessionContainer.get();
    }
    public static void setSession(HttpSession session){
        sessionContainer.set(session);
    }

    public static void removeSession(){
        sessionContainer.remove();
    }
}
