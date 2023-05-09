package com.xyc.fruit.dao.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
    工具类
    实例化一次连接池对象
      static{
      }
     方法
        对外提供连接
        回收方法
 */
public class JdbcUtils {
    private static DataSource dataSource = null; // 连接池对象
    // 线程常量
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        // 初始化连接池对象
        Properties properties = new Properties();
        InputStream ips = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection == null){
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return connection;
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null){
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
