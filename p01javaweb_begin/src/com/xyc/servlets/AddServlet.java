package com.xyc.servlets;


import com.xyc.fruit.dao.FruitDao;
import com.xyc.fruit.dao.FruitImpl;
import com.xyc.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");
        System.out.println(fname+" "+price+" "+fcount+" "+remark);
        Fruit fruit = new Fruit(0,fname,price,fcount,remark);
        FruitImpl fruitImpl = new FruitImpl();
        int i = 0;
        try {
            i = fruitImpl.insertFruit(new Fruit(0,fname,price,fcount,remark));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}