package cn.eastlegend.helper;

import cn.eastlegend.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author java_shj
 * @desc bean助手类
 * @createTime 2019/12/4 11:23
 **/
public class BeanHelper {

    /**
     * 定义bean映射（用户存放bean类与bean实例的映射关系），创建了bean实例
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls : beanClassSet) {
            Object instance = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, instance);
        }
    }

    /**
     * 获取Bean映射
     * @return Bean实例集合
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     * @param cls 要获取的Bean实例的Class对象
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if(!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("根据bean名称无法获取到bean:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置bean实例
     * 把bean实例，比如代理对象放入框架底层的BEAN_MAP中，然后通过IOC注入
     * @param clazz
     * @param obj
     */
    public static void setBean(Class<?> clazz, Object obj) {
        BEAN_MAP.put(clazz, obj);
    }



}
