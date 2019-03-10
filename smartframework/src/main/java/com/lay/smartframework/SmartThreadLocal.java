package com.lay.smartframework;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * 添加SmartThreadLocal
 * @param <T>
 */
public class SmartThreadLocal<T> {
    private Map<Thread,T> container= Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value){
        container.put(Thread.currentThread(),value);
    }

    public T get(){
        Thread thread = Thread.currentThread();
        T value=container.get(thread);
        if(value==null&&!container.containsKey(thread)){
            value=initialValue();
            container.put(thread,value);

        }
        return value;

    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    private T initialValue() {
        return  null;
    }


}
