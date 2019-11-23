package cn.eastlegend.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author java_shj
 * @desc 集合工具类
 * @createTime 2019/11/23 14:08
 **/
public class CollectionUtil {

    /**
     * 判断collection是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     * @param map
     * @return
     */
    public static boolean isMapEmpty(Map<?,?> map) {
        return MapUtils.isEmpty(map);
    }

    public static boolean isMapNotEmpty(Map<?,?> map) {
        return !isMapEmpty(map);
    }
}
