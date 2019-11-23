package cn.eastlegend.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author java_shj
 * @desc    把string类型转换成其他类型工具类
 * @createTime 2019/11/22 21:55
 **/
public class CastUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CastUtil.class);

    //转为string,默认值（如果对象为null时）为""
    public static String cast2String(Object object) {
        return cast2String(object, "");
    }
    //转为string
    public static String cast2String(Object object, String defaultValue) {
        return object !=null ? String.valueOf(object) : defaultValue;
    }
    //转为double,默认值为0.0
    public static double cast2Double(Object object) {
        return cast2Double(object, 0);
    }
    //转为double
    public static double cast2Double(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if(object != null) {
            String stringValue = cast2String(object);
            if(StringUtils.isNotEmpty(stringValue)) {
                try {
                    doubleValue = Double.parseDouble(stringValue);
                }catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    //转为long
    public static long cast2Long(Object object) {
        return cast2Long(object, 0);
    }

    //转为long
    public static long cast2Long(Object object, long defaultValue) {
        long longValue = defaultValue;
        if(object != null) {
            String stringValue = cast2String(object);
            if (StringUtils.isNotEmpty(stringValue)) {
                try {
                    longValue = Long.parseLong(stringValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    //转为int
    public static int cast2Int(Object object) {
        return cast2Int(object, 0);

    }

    //转为int
    public static int cast2Int(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String stringValue = cast2String(object);
            if(StringUtils.isNotEmpty(stringValue)) {
                try {
                    intValue = Integer.parseInt(stringValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    //转为boolean
    public static boolean cast2Boolean(Object object) {
    return cast2Boolean(object,false);
    }
    //转为boolean
    public static boolean cast2Boolean(Object object, boolean defaultValue) {
        return object == null ? defaultValue : Boolean.parseBoolean(cast2String(object));
    }
}
