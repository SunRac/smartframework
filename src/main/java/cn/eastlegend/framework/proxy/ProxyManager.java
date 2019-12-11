package cn.eastlegend.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author java_shj
 * @desc 代理管理器
 * 输入一个目标类和一个proxyList，输出一个代理对象
 * @createTime 2019/12/9 13:29
 **/
public class ProxyManager {
    public static <T> T createPrxoy(final Class<?> targetClazz, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClazz, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClazz, targetObject, targetMethod, methodProxy, methodParams).doProxyChain();
            }
        });
    }

}
