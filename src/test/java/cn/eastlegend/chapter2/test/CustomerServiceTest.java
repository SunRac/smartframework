package cn.eastlegend.chapter2.test;

import cn.eastlegend.chapter2.model.Customer;
import cn.eastlegend.chapter2.service.CustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc    customerService单元测试
 * @createTime 2019/11/21 23:54
 **/
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Before
    public void init(){
//        DatabaseHelper.exectureSqlFile("sql/customer_init.sql");
    }

    @Test
    public void getCustomerListTest(){
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(6, customerList.size());
    }

    @Test
    public void getCustomerTest(){
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public  void  createCustomerTest(){
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "mary");
        fieldMap.put("contact", "john");
        fieldMap.put("telephone", "18612");
        fieldMap.put("email", "18612@eastlegend.cn");
        fieldMap.put("remark", "约翰");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest(){
        long id = 3;
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("deleteFlag", 0);
//        boolean result = customerService.deleteCustomer("customer", id);
//        boolean result = customerService.updateCustomer(id, fieldMap);
        boolean result = customerService.deleteCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    @After
    public void testOver(){
        System.out.println("所有方法都测试完了！");
    }
}
