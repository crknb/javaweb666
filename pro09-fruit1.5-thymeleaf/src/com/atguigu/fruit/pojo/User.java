package com.atguigu.fruit.pojo;

public class User {
    private String username;
    private String userpwd;

    public User() {
    }

    public User(String username, String userpwd) {
        this.username = username;
        this.userpwd = userpwd;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return userpwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * 设置
     * @param userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String toString() {
        return "User{username = " + username + ", userpwd = " + userpwd + "}";
    }
}
