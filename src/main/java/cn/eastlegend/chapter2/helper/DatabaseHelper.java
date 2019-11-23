package cn.eastlegend.chapter2.helper;

import cn.eastlegend.util.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author java_shj
 * @desc    数据库操作辅助类，把加载驱动和数据库的代码提取出来
 * @createTime 2019/11/23 15:40
 **/
public class DatabaseHelper {

    /**
     * 把加载驱动，和数据库配置信息的代码抽取出来
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    //初始化QueryRunner
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    //使用threadLocal来保存当前线程的数据库连接
    private static final ThreadLocal<Connection> Thread_Local_4Conn = new ThreadLocal<>();

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties props = PropsUtil.loadProps("config.properties");
        DRIVER = props.getProperty("jdbc.driver");
        URL = props.getProperty("jdbc.url");
        USERNAME = props.getProperty("jdbc.username");
        PASSWORD = props.getProperty("jdbc.password");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载数据库驱动异常：", e);
        }
    }


    /**
     * 把获取数据库连接的代码抽取出来
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("查询数据库异常：", e);
        }
        return connection;
    }

    /**
     * 把关闭数据库的代码抽取出来
     */
    public static void closeConnection(Connection connection) {
        if(connection != null ) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("关闭数据连接异常：", e);
            }
        }
    }

    /**
     * 查询实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, Connection connection,String sql, Object... params) {
        List<T> entityList = null;
        try {
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<>(entityClass));
        } catch (SQLException e) {
            LOGGER.error("查询实体列表异常：", e);
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
        return entityList;

    }


}
