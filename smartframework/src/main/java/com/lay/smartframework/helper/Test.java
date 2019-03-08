package com.lay.smartframework.helper;

import com.lay.smartframework.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 16:32 2019/1/31
 * @Modified By:IntelliJ IDEA
 */
public class Test {
    private static final Logger LOGGER= LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Field[] mans = Class.class.getDeclaredFields();
        for (Field man : mans) {
            java.lang.Class<?> declaringClass = man.getDeclaringClass();
            java.lang.Class<?> type = man.getType();
            boolean result=Man.class.isAssignableFrom(Man.class);
            boolean anAbstract = Modifier.isAbstract(Person.class.getModifiers());
            boolean anInterface = Modifier.isInterface(Person.class.getModifiers());
            boolean anInterface1 = Person.class.isInterface();
            java.lang.Class<?> aClass = java.lang.Class.forName("com.lay.smartframework.helper.Test$Person");
            LOGGER.error("logger message:{}",man);
            System.out.println(man);
        }

    }
    static class Class{
        private Man man;
    }


    static class Man implements Person{

    }
    interface  Person{

    }
}
