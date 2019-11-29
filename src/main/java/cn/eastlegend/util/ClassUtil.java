package cn.eastlegend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author java_shj
 * @desc  提供与类操作有关的方法，获取类加载器、加载类、获取指定包下的所有类等
 * @createTime 2019/11/28 16:22
 **/
public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className 全路径类名
     * @param isInitialized 是否执行类的静态代码块
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
             cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载类："+className +" 异常", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定包下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {

        return null;
    }

}
