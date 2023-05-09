package com.xyc.fruit.dao;

import com.xyc.fruit.dao.utils.BaseDao;
import com.xyc.fruit.pojo.Fruit;

import java.sql.SQLException;

public class FruitImpl extends BaseDao implements FruitDao{

    @Override
    public int insertFruit(Fruit fruit) throws SQLException {
        return executeUpdate("insert into fruit(fname,price,fcount,remark) values(?,?,?,?)");
    }
}
