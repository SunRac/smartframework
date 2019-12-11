package cn.eastlegend.helper;

import cn.eastlegend.annotation.Controller;
import cn.eastlegend.annotation.Service;
import cn.eastlegend.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author java_shj
 * @desc 类操作助手类
 * @createTime 2019/12/3 17:36
 **/
public final class ClassHelper {
    /**
     * 定义类集合(存放所加载的类)
     */
    private static final Set<Class<?>> CLASS_SET ;
    static {
        String packageName = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(packageName);
    }

    /**
     * 获取应用对应包下所有类
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取所有带自定义service注解的类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取所有带Controller注解的类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Controller.class));
            classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有bean类
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getControllerClassSet());
        beanClassSet.addAll(getServiceClassSet());
        return beanClassSet;
    }
    /**
     * 获取应用包下某父类（或接口）的所有子类
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClazz) {
        Set<Class<?>> beanClassSet = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if(superClazz.isAssignableFrom(clazz) && superClazz != clazz) {
                beanClassSet.add(clazz);
            }
        }
        return beanClassSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClazz) {
        Set<Class<?>> beanClassSet = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if(clazz.isAnnotationPresent(annotationClazz)) {
                beanClassSet.add(clazz);
            }
        }
        return beanClassSet;
    }


}
