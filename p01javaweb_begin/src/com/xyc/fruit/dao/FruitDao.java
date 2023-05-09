package com.xyc.fruit.dao;

import com.xyc.fruit.pojo.Fruit;

import java.sql.SQLException;

public interface FruitDao {
    int insertFruit(Fruit fruit) throws SQLException;

}
