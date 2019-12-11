package cn.eastlegend.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author java_shj
 * @desc  切面代理抽象类--模板方法设计模式
 * @createTime 2019/12/9 21:38
 **/
public abstract class AspectProxy implements Proxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);


    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> clazz = proxyChain.getTargetClazz();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] methodParams = proxyChain.getMethodParams();
        begin();
        try {
            if (intercept(clazz, targetMethod, methodParams)) {
                //执行代理逻辑
                before(clazz, targetMethod, methodParams);
                result = proxyChain.doProxyChain();
                after(clazz, targetMethod, methodParams);

            } else {
                //执行具体的业务逻辑
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("代理出现异常：", e);
            error(clazz, targetMethod, methodParams);
            throw e;
        } finally {
            end();
        }
        return null;
    }

    public void begin(){

    }
    public boolean intercept(Class<?> clazz, Method method, Object[] params) throws Throwable{
        return true;
    }
    public abstract void before(Class<?> clazz, Method method, Object[] params);
    public abstract void after(Class<?> clazz, Method method, Object[] params);
    public void error(Class<?> clazz, Method method, Object[] params){

    }
    public void end(){

    }
}
