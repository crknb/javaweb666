package com.xyc.fruit.dao.utils;

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

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            T t = clazz.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                String name = metaData.getColumnLabel(i);
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JdbcUtils.freeConnection();
        }
        return list;
    }
}
