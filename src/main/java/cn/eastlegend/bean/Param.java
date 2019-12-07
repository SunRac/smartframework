package cn.eastlegend.bean;

import cn.eastlegend.util.CastUtil;

import java.util.Map;

/**
 * @author java_shj
 * @desc 请求参数对象
 * @createTime 2019/12/6 13:40
 **/
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
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
}
