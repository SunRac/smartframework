package cn.eastlegend.chapter2.helper;

import cn.eastlegend.util.CollectionUtil;
import cn.eastlegend.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.Keymap;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<>();

//    数据库连接池
    private static final BasicDataSource DATA_SOURCE;

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
        /*try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载数据库驱动异常：", e);
        }*/

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);
    }


    /**
     * 把获取数据库连接的代码抽取出来
     * @return
     */
    public static Connection getConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if( connection == null) {
            try {
//                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("查询数据库异常：", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /**
     * 把关闭数据库的代码抽取出来
     */
    public static void closeConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if(connection != null ) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("关闭数据连接异常：", e);
            } finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }

    /**
     * 查询实体列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass,String sql, Object... params) {
        List<T> entityList = null;
        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<>(entityClass));
        } catch (SQLException e) {
            LOGGER.error("查询实体列表异常：", e);
            throw new RuntimeException(e);
        }
        //把连接交给数据库连接池来管理，不需要再手动关闭连接了
        /*finally {
            closeConnection();
        }*/
        return entityList;
    }

    /**
     * 查询单个实体
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity = null;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("查询单个实体异常", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * 很多时候查询并不是恰好返回的实体
     * 方案1：更普遍的一个方法：查询后返回一个map
     * 方案2：新增一个对应的实体类
     */
    public static List<Map<String, Object>> executeQuery(String tableName, Map<String, Object> fieldMap) {
        List<Map<String, Object>> result = null;
        String sql = "SELECT * FROMM " + tableName;
        Object[] params = fieldMap.values().toArray();
        try {
            if(CollectionUtil.isMapEmpty(fieldMap)){
                sql += " WHERE ";
                StringBuilder columns = new StringBuilder();
                for (String key : fieldMap.keySet()) {
//                columns.append(key + "=" + fieldMap.get(key) + " AND " );
                    columns.append(key + "=? AND " );
                }
                sql += columns.substring(0, columns.lastIndexOf("AND"));
                Connection connection = getConnection();
                result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
            }
        } catch (SQLException e) {
            LOGGER.error("executeQuery查询异常，sql是：" + sql + "params是：" + params, e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 通用的更新方法，包括：update,insert,delete
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, params);
        } catch (SQLException e) {
            LOGGER.error("executeUpdate执行异常，sql是：" + sql + "params是：" + params, e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 插入实体
     */
    public static <T> boolean insertEntity(String tableName, Map<String, Object> fieldMap) {
        if(CollectionUtil.isMapEmpty(fieldMap)) {
            LOGGER.error("插入实体异常：fieldMap is empty");
            return false;
        }

        String sql = "INSERT INTO " + tableName;
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieleName : fieldMap.keySet()) {
            columns.append(fieleName).append(",");
            values.append("?,");
        }
        columns.replace(columns.lastIndexOf(","), columns.length(), ")");
        values.replace(values.lastIndexOf(","), values.length(), ")");
        sql += columns + " values " + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) ==1;
    }

    /**
     * 更新实体
     */
    public static boolean updateEntity(String tableName, long id, Map<String, Object> fieldMap) {
        if(CollectionUtil.isMapEmpty(fieldMap)) {
            LOGGER.error("更新实体异常，fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + tableName+ " SET ";
        StringBuilder colums = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            colums.append(fieldName + "=?,");
        }
        sql += colums.substring(0, colums.lastIndexOf(",")) + " WHERE id = " + id;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) ==1;
    }

    /**
     * 删除实体
     * 问题：生产环境一般都是逻辑删除，此处仅为示例
     */
    public static boolean deleteEntity(String talbeName, long id) {
        String sql = "DELEETE FROM " + talbeName + " WHERE id=" + id;
        return executeUpdate(sql) == 1;
    }
    public static boolean deleteEntity(String tableName, long id, Map<String, Object> fieldMap) {
        return updateEntity(tableName, id, fieldMap);
    }

    /**
     * 执行sql文件，比如进行测试数据库的初始化等
     * @param filePath classpath下的sql文件
     */
    public static void exectureSqlFile(String filePath) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String sql;
            while ((sql = bufferedReader.readLine()) != null) {
                executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("执行sql文件异常：", e);
            throw new RuntimeException(e);
        }

    }



}
