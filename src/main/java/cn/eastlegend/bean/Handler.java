package cn.eastlegend.bean;

import java.lang.reflect.Method;

/**
 * @author java_shj
 * @desc 封装Action信息
 * @createTime 2019/12/5 13:59
 **/
public class Handler {
    /**
     * controller类
     */
    private Class<?> controllerClass;
    /**
     * action方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
