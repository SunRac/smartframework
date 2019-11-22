package cn.eastlegend.chapter2.service;

import cn.eastlegend.chapter2.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc 提供客户数据服务
 * @createTime 2019/11/21 23:46
 **/
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList(String keyword){
        return null;
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
