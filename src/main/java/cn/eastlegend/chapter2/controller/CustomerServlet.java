package cn.eastlegend.chapter2.controller;

import cn.eastlegend.chapter2.model.Customer;
import cn.eastlegend.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author java_shj
 * @desc
 * 随着需求扩展，请求个数不断增长，每一个请求都要增加一个Servlet，Servlet数量也会不断增多能否把多个请求对应一个类中的多个方法呢？
 * @createTime 2019/11/21 23:43
 **/
@WebServlet("/customer")
//@WebServlet("/customer_create")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    /**
     * 进入创建用户界面
     * 客户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.getCustomerList();
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
    }

    /**【】
     * 处理创建用户请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
