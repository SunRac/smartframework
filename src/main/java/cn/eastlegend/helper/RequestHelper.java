package cn.eastlegend.helper;

import cn.eastlegend.bean.FormParam;
import cn.eastlegend.bean.Param;
import cn.eastlegend.util.CodeUtil;
import cn.eastlegend.util.StreamUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * @author java_shj
 * @desc 从Request中初始化Param助手类
 * @createTime 2019/12/20 22:59
 **/
public class RequestHelper {
    private static final Logger LOGGER =  LoggerFactory.getLogger(RequestHelper.class);
    /**
     * 创建请求对象
     * 适用于普通请求，不包含上传文件字段
     * 上传文件时的请求对象Param参见UploadHelper.createParam(request)
     */

    public static Param createParam(HttpServletRequest request) {
        ArrayList<FormParam> formparamList = new ArrayList<>();
        formparamList.addAll(parseParameterName(request));
        formparamList.addAll(parseInputStream(request));
        return new Param(formparamList);
    }

    /**
     *从request中获取FormParam列表
     * @param request
     * @return
     */
    private static List<FormParam> parseParameterName(HttpServletRequest request) {
        ArrayList<FormParam> formparamList = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String fieldName = parameterNames.nextElement();
            //获取每个字段对应的值
            String[] fieldValues = request.getParameterValues(fieldName);
            if(ArrayUtils.isNotEmpty(fieldValues)) {
                Object filedValue ;
               if(fieldValues.length == 1) {
                   filedValue = fieldValues[0];
               } else {
                   StringBuffer sb = new StringBuffer("");
                   for (String fieldValue : fieldValues) {
                       sb.append(",").append(fieldValue);
                   }
                   filedValue = sb.toString().substring(1);
               }
                formparamList.add( new FormParam(fieldName, filedValue));
            }
        }
        return formparamList;
    }

    /**
     *get方式获取请求参数嘛？
     * @param request
     * @return
     */
    private static List<FormParam> parseInputStream(HttpServletRequest request) {
        ArrayList<FormParam> formparamList = new ArrayList<>();
        try {
            String body = CodeUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if(StringUtils.isNotEmpty(body)){
                String[] kvs = StringUtils.split(body, "&");
                if(ArrayUtils.isNotEmpty(kvs)) {
                    for (String kv : kvs) {
                        String[] nameValue = StringUtils.split(kv, "=");
                        if(ArrayUtils.isNotEmpty(nameValue) && nameValue.length == 2) {
                            String name = nameValue[0];
                            String value = nameValue[1];
                            formparamList.add(new FormParam( name, value));
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("从Request中获取FormParam异常：", e);
        }
        return formparamList;
    }

}
