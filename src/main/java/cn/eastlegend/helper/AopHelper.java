package cn.eastlegend.helper;

import cn.eastlegend.annotation.Aspect;
import cn.eastlegend.framework.proxy.AspectProxy;
import cn.eastlegend.framework.proxy.ControllerAspect;
import cn.eastlegend.framework.proxy.Proxy;
import cn.eastlegend.framework.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.AnnotationValue;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author java_shj
 * @desc AOP助手类，用于aop代理类
 * @createTime 2019/12/9 22:30
 **/
public class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
    //静态初始化AOP框架
    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Class<?> targetClazz : targetMap.keySet()) {
                List<Proxy> proxyList = targetMap.get(targetClazz);
                Object proxy = ProxyManager.createPrxoy(targetClazz, proxyList);
                BeanHelper.setBean(targetClazz, proxy);
            }
        } catch (Exception e) {
            LOGGER.error("初始化AOP框架异常：", e);
        }
    }

    /**
     * 获取带有某种Aspect注解的clazz集合
     * @param aspect
     * @return Set<Class<?>>
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> targetClazzSet = new HashSet<>();
        Class<? extends Annotation> annnotationValue = aspect.value();
        if (annnotationValue != null && annnotationValue != Aspect.class) {
            targetClazzSet.addAll(ClassHelper.getClassSetByAnnotation(annnotationValue));
        }
        return targetClazzSet;
    }

    /**
     * 创建代理类与目标类之间的映射关系
     * @return
     * @throws Exception
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        //获取所有切面实现类
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClazz : proxyClassSet) {
            Aspect aspect = proxyClazz.getAnnotation(Aspect.class);
            //获取某种Aspect注解值对应的的clazz集合，入Aspect(Controller.class)注解
            Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
            proxyMap.put(proxyClazz, targetClassSet);
        }
        return proxyMap;
    }

    //目标类与代理对象列表之间的映射关系
    private static Map< Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map< Class<?>, List<Proxy>> targetMap = new HashMap<>();
        //遍历所有代理类与目标的映射关系
        for (Class<?> proxyClazz : proxyMap.keySet()) {
            Set<Class<?>> targetClassSet = proxyMap.get(proxyClazz);
            for (Class<?> targetClazz : targetClassSet) {
                Proxy proxy = (Proxy) proxyClazz.newInstance();
                if(targetMap.containsKey(targetClazz)) {
                    targetMap.get(targetClazz).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClazz, proxyList);
                }
            }
        }
    return  targetMap;
    }
}
