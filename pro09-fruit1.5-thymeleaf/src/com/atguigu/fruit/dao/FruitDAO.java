package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface FruitDAO {
    //获取所有的库存列表信息
    List<Fruit> getFruitList(Integer pageNo);
    Fruit getFruitById(Integer fid);
    void updateFruit(Fruit fruit);
    void delFruit(Integer fid);
    void addFruit(Fruit fruit);
    boolean login(User user);
    int getFruitCount();
}
