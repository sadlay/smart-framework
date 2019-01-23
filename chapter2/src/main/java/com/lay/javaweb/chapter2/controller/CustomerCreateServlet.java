package com.lay.javaweb.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 创建客户
 * @Author: lay
 * @Date: Created in 15:15 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {

    //进入 创建客户 页面
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    //处理 创建客户 请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
