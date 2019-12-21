package cn.eastlegend.bean;

import cn.eastlegend.util.CastUtil;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc 请求参数对象
 * @createTime 2019/12/6 13:40
 **/
public class Param {
    private Map<String, Object> paramMap;

    //重新封装，支持文件上传
    List<FormParam> formParamList;
    List<FileParam> fileParamList;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }
    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    /**
     * 根据参数名获取long型参数值
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.cast2Long(paramMap.get(name));
    }

    /**
     * 获取所有参数信息
     * @return paramMap
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * 根据创建Param对象是传入的List<FormParam>获取普通请求参数映射
     * 表单上传filed域的字段名：字段值组成的Map
     */
    public Map<String, Object> getFieldMap() {
        return null;
    }

    /**
     * 根据创建Param对象是传入的List<FileParam>获取上传文件参数映射
     * 表单上传filed域的字段名：FileParam组成的Map
     */
    public Map<String, List<FileParam>> getFielMap() {
        return null;
    }
}
