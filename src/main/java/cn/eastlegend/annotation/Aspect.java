package cn.eastlegend.annotation;

import java.lang.annotation.*;

/**
 * @author java_shj
 * @desc 自定义切面注解
 * @createTime 2019/12/9 13:04
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解
     * 注解中有一个value属性
     */
    Class<? extends Annotation> value();
}
