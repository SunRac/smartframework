package cn.eastlegend.chapter2.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author java_shj
 * 代理没有接口的类怎么办？
 * @desc 使用CGLib再运行期间动态生成字节码--动态生成代理类
 * CGLib 提供方法级别的代理
 * @createTime 2019/12/8 20:33
 **/
public class CGLibProxy implements MethodInterceptor {

    //改成单例模式
    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy() {

    }

    public static CGLibProxy getInstance() {
        return instance;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //需要重写1： 前置通知
        before();
        //调用方法
        Object result = methodProxy.invokeSuper(obj, args);
        //需要重写2： 后置通知
        after();
        return result;
    }

    private void before(){
        System.out.println("before CGLIB");
    }
    private void after(){
        System.out.println("after CGLIB");
    }

    //封装获取代理的方法
    public <T> T getProxy(Class<T> clazz) {
        return (T) Enhancer.create(clazz, this);
    }
}
