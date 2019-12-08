package cn.eastlegend.chapter2.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author java_shj
 * @desc 通用的动态代理对象
 * @createTime 2019/12/8 14:21
 **/
public class DynamicProxy implements InvocationHandler {
    //被代理的对象
    private Object target;
    public DynamicProxy(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         before();
        Object result = method.invoke(target, args);
         after();
        return result;
    }

    private void before(){
        System.out.println("before");
    }
    private void after(){
        System.out.println("after");
    }
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
