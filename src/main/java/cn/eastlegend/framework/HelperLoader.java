package cn.eastlegend.framework;

import cn.eastlegend.helper.BeanHelper;
import cn.eastlegend.helper.ClassHelper;
import cn.eastlegend.helper.ControllerHelper;
import cn.eastlegend.helper.IocHelper;
import cn.eastlegend.util.ClassUtil;

/**
 * @author java_shj
 * @desc 加载相应的Helper类
 * @createTime 2019/12/6 13:00
 **/
public final class HelperLoader {
    public static void init() {
        Class<?>[] clazzList = {ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> clazz : clazzList) {
            ClassUtil.loadClass(clazz.getName(), true);
        }
    }
}
