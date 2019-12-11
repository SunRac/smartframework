package cn.eastlegend.framework;

import cn.eastlegend.helper.*;
import cn.eastlegend.util.ClassUtil;

/**
 * @author java_shj
 * @desc 加载相应的Helper类
 * @createTime 2019/12/6 13:00
 **/
public final class HelperLoader {
    public static void init() {
        //Aophelper要位于IocHelper之前，因为需要先获取到代理对象才能注入
        Class<?>[] clazzList = {ClassHelper.class, BeanHelper.class, AopHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> clazz : clazzList) {
            ClassUtil.loadClass(clazz.getName(), true);
        }
    }
}
