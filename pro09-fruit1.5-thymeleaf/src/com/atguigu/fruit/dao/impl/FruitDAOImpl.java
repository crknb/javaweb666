package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.pojo.User;
import com.atguigu.myssm.dao.BaseDao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl extends BaseDao implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(Integer pageNo){
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.atguigu.fruit.pojo.Fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (List<Fruit>) executeQuery(aClass,"select * from fruit limit ?,5",(pageNo-1)*5);


    }

    @Override
    public Fruit getFruitById(Integer fid) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.atguigu.fruit.pojo.Fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<?> objects = executeQuery(aClass, "select * from fruit where fid=?", fid);
        return  (Fruit) objects.get(0);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        try {
            super.executeUpdate("update fruit set fname=?,price=?,fcount=?,remark=? where fid=?",fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delFruit(Integer fid) {
        try {
            super.executeUpdate("delete from fruit where fid = ?",fid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFruit(Fruit fruit) {
        try {
            super.executeUpdate("insert into fruit(fname,price,fcount,remark) values(?,?,?,?)",
                    fruit.getFname(),fruit.getPrice(),fruit.getPrice(),fruit.getRemark());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(User user) {
        try {
            Class<?> aClass = Class.forName("com.atguigu.fruit.pojo.User");
            List<?> ts = super.executeQuery(aClass,"select userpwd from user where username = ?", user.getUsername());
            if (ts.size()!=0) {
                User o = (User) ts.get(0);
                if (o.getUserpwd().equals(user.getUserpwd())) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getFruitCount() {
        List<?> objects = new ArrayList<>();
        try {
            Class<?> aClass = Class.forName("com.atguigu.fruit.pojo.Fruit");
            objects = super.executeQuery(aClass, "select * from fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects.size();
    }
}
