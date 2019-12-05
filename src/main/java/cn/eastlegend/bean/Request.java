package cn.eastlegend.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author java_shj
 * @desc 封装请求信息
 * @createTime 2019/12/5 13:51
 **/
public class Request {

    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求路径
     */
    private String requestPath;
    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public int hashCOde() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }
}
