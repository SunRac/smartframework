package cn.eastlegend.helper;

import cn.eastlegend.annotation.Inject;
import cn.eastlegend.util.ReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author java_shj
 * @desc 控制反转（依赖注入）助手类
 * @createTime 2019/12/4 17:45
 **/
public final class IocHelper {
    static {
        //获取所有bean类与bean实例之间的映射关系
        Map<Class<?>, Object> beanMap= BeanHelper.getBeanMap();
        for (Class<?> cls : beanMap.keySet()) {
            Object beanInstance = beanMap.get(cls);
            //获取bean的所有成员变量
            Field[] declaredFields = cls.getDeclaredFields();
            if(ArrayUtils.isNotEmpty(declaredFields)) {
                for (int i = 0; i < declaredFields.length; i++) {
                    Field declaredField = declaredFields[i];
                    if(declaredField.isAnnotationPresent(Inject.class)) {
                        //从获取字段所属bean类
                        Class<?> beanFieldType = declaredField.getType();
                        Object beanFieldInstance = beanMap.get(beanFieldType);
                        if(beanFieldInstance != null) {
                            //通过反射初始化实例变量的值
                            ReflectionUtil.setField(beanInstance, declaredField, beanFieldInstance);
                        }

                    }
                }
            }
        }
        
    }
}
