package cn.eastlegend.helper;

import cn.eastlegend.annotation.Action;
import cn.eastlegend.bean.Handler;
import cn.eastlegend.bean.Request;
import cn.eastlegend.util.CollectionUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author java_shj
 * @desc 控制器助手类
 * @createTime 2019/12/5 14:03
 **/
public class ControllerHelper {
    /**
     * 用于存放请求与处理器的映射关系(简称 Action Map)
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        //获取说有controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for (Class<?> clazz : controllerClassSet) {
                //获取controller类的所有方法
                Method[] methods = clazz.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        //过滤得到带有Action注解的方法
                        if(method.isAnnotationPresent(Action.class)) {
                            //获取Action注解的配置
                            Action actionAnnotation = method.getAnnotation(Action.class);
                            String mapping = actionAnnotation.value();
                            if(StringUtils.isNotEmpty(mapping) && mapping.matches("\\w+:\\w*")) {
                                //把配置的注解值分割成数组
                                String[] mapVal = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(mapVal) && mapVal.length == 2){
                                    String requestMethod = mapVal[0];
                                    String requesPath = mapVal[1];
                                    Request request = new Request(requestMethod, requesPath);
                                    Handler handler = new Handler(clazz, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据Request获取Handler
     * @param request
     * @return
     */
    public static Handler getHandler(Request request) {
        return ACTION_MAP.get(request);
    }

}
