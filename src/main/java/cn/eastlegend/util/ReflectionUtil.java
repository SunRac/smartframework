package cn.eastlegend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author java_shj
 * @desc 封装java反射相关的API
 * @createTime 2019/12/3 17:56
 **/
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 实例化bean
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();

        } catch (Exception e) {
            LOGGER.error("ReflectionUtil实例化bean异常：", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param object 调用方法的对象
     * @param method 方法
     * @param args 参数
     * @return
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        method.setAccessible(true);
        try {
            result = method.invoke(object, args);
        } catch (Exception e) {
            LOGGER.error("ReflectionUtil调用方法异常：", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param object
     * @param field
     * @param value
     */
    public static void setField(Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (Exception e) {
            LOGGER.error("ReflectionUtil设置成员变量的值异常：", e);
            throw new RuntimeException(e);
        }
    }
}
