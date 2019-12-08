package cn.eastlegend.chapter2.test;

import java.lang.reflect.Proxy;

/**
 * @author java_shj
 * @desc 动态代理练习
 * @createTime 2019/12/8 20:19
 **/
public class DynamicProxyTest {
    public static void main(String[] args) {
//        dynamicProxyInterfaceTest();
        //基于CGLib动态代理  1、普通方式，先new
//        CGLibProxy cgLibProxy = new CGLibProxy();
        //2、单例模式的CGLibProxy
        Hello proxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        proxy.hello();
    }

    private static void dynamicProxyInterfaceTest() {
        HelloImpl hello = new HelloImpl();
        //通用的动态代理对象----动态代理模板，要根据模板来生成实际的代理对象
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        //具体的动态代理对象
//        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), dynamicProxy);
        //把Proxy.newProxyInstance()方法封装下
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.hello();
    }
}
