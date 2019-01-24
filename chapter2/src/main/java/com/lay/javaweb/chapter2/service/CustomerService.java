package com.lay.javaweb.chapter2.service;

import com.lay.javaweb.chapter2.helper.DatabaseHelper;
import com.lay.javaweb.chapter2.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 15:17 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class CustomerService {

    //获取客户列表
    public List<Customer> getCustomerList(){
        String sql="select * from customer";
        List<Customer> customerList= DatabaseHelper.queryEntityList(Customer.class,sql);
        return customerList;
    }

    //获取客户列表
    public List<Map<String, Object>> getCustomerListMap(){
        String sql="select * from customer where contact=?";
        String param="Jack";
        List<Map<String, Object>> customerList= DatabaseHelper.executeQuery(sql,param);
        return customerList;
    }

    //获取客户
    public Customer getCustomer(Long id){
        return null;
    }

    //创建客户
    public boolean createCustomer(Customer customer){
        return DatabaseHelper.insertEntity(customer);
    }

    //更新客户
    public boolean updateCustomer(Customer customer){
        return DatabaseHelper.updateEntityById(customer);
    }

    //全量更新
    public boolean updateCustomerAll(Customer customer){
        return DatabaseHelper.updateAllEntityById(customer);
    }

    //删除客户
    public boolean deleteCustomer(Long id){
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }
}
