package cn.eastlegend.constant;

/**
 * @author java_shj
 * @desc   提供相关配置项常量
 * @createTime 2019/11/28 16:01
 **/
public interface ConfigConstant {
//    读取哪个配置文件
    String CONFIG_FILE = "smart.properties";

    String JDBC_DRIVER = "jdbc.driver";
    String JDBC_URL = "jdbc.url";
    String JDBC_USERNAME = "jdbc.username";
    String JDBC_PASSWORD = "jdbc.password";
//    配置文件的配置项的key
    String APP_BASE_PACKAGE = "app.base_package";
    String APP_JSP_PATH = "app.jsp_path";
    String APP_ASSET_PATH = "app.asset_path";

    String APP_UPLOAD_LIMIT = "app.uplaod_limit";

}
