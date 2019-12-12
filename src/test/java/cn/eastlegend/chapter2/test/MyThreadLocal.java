package cn.eastlegend.chapter2.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author java_shj
 * @desc 自己的ThreadLocal
 * @createTime 2019/12/11 13:46
 **/
public class MyThreadLocal<T> {
    //两种map都是线程安全的
//    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<>());
    private Map<Thread, T> container = new ConcurrentHashMap<>();

    public T get(){
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if(value == null && !container.containsKey(thread)) {
            value = initialValue();
        }
        return value;
    }

    public void set(T value){
        container.put(Thread.currentThread(), value);
    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    protected T initialValue() {
        return null;
    }

}
