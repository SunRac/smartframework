package cn.eastlegend.chapter2.service;

import cn.eastlegend.chapter2.helper.DatabaseHelper;
import cn.eastlegend.chapter2.model.Customer;
import cn.eastlegend.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author java_shj
 * @desc 提供客户数据服务,比较简单，就不用接口了
 * @createTime 2019/11/21 23:46
 **/
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);


    /**
     * 问题1：加载驱动、数据库连接的方法其他类无法复用，可以抽取到一个工具类中
     * 问题2：执行sql、关闭连接有太多的try catch也抽取出来
     * 问题3：把数据库映射到bean，手动来映射很麻烦，手麻，使用dbUtils来帮助自动映射
     * 问题4：确保一个线程只有一个connection，在工具类中使用ThreadLocal来保存connection
     */
 /*   private static final String DRIVER;
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
    }*/
    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList(String keyword){
        return null;
    }

    public List<Customer> getCustomerList() {
        String sql = "select * from customer";
        Connection connection = DatabaseHelper.getConnection();
        List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, connection, sql);
        return customerList;
     /*   Connection connection = null;
        String sql = "select * from customer";
        ArrayList<Customer> customers = new ArrayList<>();
        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection = DatabaseHelper.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setName(resultSet.getString("name"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setRemark(resultSet.getString("remark"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("查询数据库异常：", e);
        } finally {
           *//* if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("关闭数据连接异常：", e);
                }
            }*//*
           DatabaseHelper.closeConnection(connection);
        }*/
    }

    public Customer getCustomer(long id){
        return null;
    }

    public boolean createCustomer(Map<String, Object> fieldMap){
        return false;
    }

    public boolean updateCustomer(long id ,Map<String, Object> fieldMap){
        return false;
    }

    public boolean deleteCustomer(long id){
        return false;
    }
}
