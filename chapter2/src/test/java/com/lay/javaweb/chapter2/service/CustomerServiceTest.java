package com.lay.javaweb.chapter2.service;

import com.lay.javaweb.chapter2.helper.DatabaseHelper;
import com.lay.javaweb.chapter2.model.Customer;
import com.lay.javaweb.chapter2.util.BeanUtil;
import com.lay.javaweb.chapter2.util.support.BeanKit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:单元测试
 * @Author: lay
 * @Date: Created in 15:26 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService=new CustomerService();
    }
    @Before
    public void init() throws IOException {
        //初始化数据库
        String file="sql/customer_init.sql";
        DatabaseHelper.executeSqlFile(file);
    }

    @Test
    public void getCustomerList() {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    //获取客户列表
    public void getCustomerListMap(){
        List<Map<String, Object>> list=customerService.getCustomerListMap();
        Assert.assertNotNull(list);
    }

    @Test
    public void getCustomer() {
        Long id=1L;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomer() {
        Customer customer=new Customer();
        customer.setName("customer100");
        customer.setContact("John");
        customer.setTelephone("13212345421");
        customer.setEmail("john@gmail.com");
        boolean result = customerService.createCustomer(customer);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomer() {
        Long id=1L;
        Customer customer=new Customer();
        customer.setId(id);
        customer.setContact("Eric");
        boolean result = customerService.updateCustomer(customer);
        Assert.assertTrue(result);
    }

    @Test
    public void updatCustomerAll() {
        Long id=1L;
        Customer customer=new Customer();
        customer.setId(id);
        customer.setContact("John");
        boolean result = customerService.updateCustomerAll(customer);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomer() {
        Long id=1L;
        boolean result=customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }

    @Test
    public void testBeanKit() {
        Customer customer=new Customer();
        customer.setName("customer100");
        customer.setContact("John");
        customer.setTelephone("13212345421");
        customer.setEmail("john@gmail.com");
        Map<String, Object> objectMap = BeanKit.beanToMap(customer);
        Map<String, Object> objectMap1 = BeanUtil.beanToMapWithNull(customer);
        objectMap.values().toArray();
    }
}