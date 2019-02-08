package com.lay.smartframework;

import com.lay.smartframework.helper.*;
import com.lay.smartframework.util.ClassUtil;

/**
 * @Description: 加载相应的Helper类
 * @Author: lay
 * @Date: Created in 23:10 2019/1/30
 * @Modified By:IntelliJ IDEA
 */
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
