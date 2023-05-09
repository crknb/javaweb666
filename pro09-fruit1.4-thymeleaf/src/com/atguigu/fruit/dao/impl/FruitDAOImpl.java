package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.dao.BaseDao;



import java.util.List;

public class FruitDAOImpl extends BaseDao implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(){
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.atguigu.fruit.pojo.Fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (List<Fruit>) executeQuery(aClass,"select * from fruit");


    }
}
