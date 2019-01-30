package com.lay.smartframework;

import com.lay.smartframework.helper.BeanHelper;
import com.lay.smartframework.helper.ClassHelper;
import com.lay.smartframework.helper.IocHelper;
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
                IocHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
