package com.atguigu.myssm.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    public int executeUpdate(String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // params 是可变参数可以当作数组使用
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        // 是否回收连接
        if (connection.getAutoCommit()) {
            // 没开启事务 回收
            JdbcUtils.freeConnection();
        }
        return i;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                try {
                    preparedStatement.setObject(i, params[i - 1]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = null;
        try {
            metaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int columnCount = 0;
        try {
            columnCount = metaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            T t = null;
            try {
                t = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= columnCount; i++) {
                Object value = null;
                try {
                    value = resultSet.getObject(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String name = null;
                try {
                    name = metaData.getColumnLabel(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Field field = null;
                try {
                    field = clazz.getDeclaredField(name);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                field.setAccessible(true);
                try {
                    field.set(t, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
        }
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection.getAutoCommit()) {
                JdbcUtils.freeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
