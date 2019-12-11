package cn.eastlegend.framework.proxy;

import cn.eastlegend.annotation.Aspect;
import cn.eastlegend.annotation.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author java_shj
 * @desc  AspectProxy实现类
 * 拦截所有Controller类的方法
 * @createTime 2019/12/9 21:52
 **/

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;
    @Override
    public void before(Class<?> clazz, Method method, Object[] params) {
    LOGGER.debug("------------begin--------------");
    LOGGER.debug(String.format("class: %s", clazz.getName()));
    LOGGER.debug(String.format("method: %s", method.getName()));
    begin = System.currentTimeMillis();

    }

    @Override
    public void after(Class<?> clazz, Method method, Object[] params) {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("------------end--------------");

    }
}
