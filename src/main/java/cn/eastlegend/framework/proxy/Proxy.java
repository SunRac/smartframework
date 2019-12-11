package cn.eastlegend.framework.proxy;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/12/9 13:07
 **/
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
