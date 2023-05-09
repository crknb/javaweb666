package com.atguigu.fruit.pojo;

public class Fruit {
    public Integer fid ;
    public String fname ;
    public Integer price ;
    public Integer fcount ;
    public String remark ;


    public Fruit() {
    }

    public Fruit(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        this.fid = fid;
        this.fname = fname;
        this.price = price;
        this.fcount = fcount;
        this.remark = remark;
    }

    /**
     * 获取
     * @return fid
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置
     * @param fid
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 获取
     * @return fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * 获取
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取
     * @return fcount
     */
    public Integer getFcount() {
        return fcount;
    }

    /**
     * 设置
     * @param fcount
     */
    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    /**
     * 获取
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "Fruit{fid = " + fid + ", fname = " + fname + ", price = " + price + ", fcount = " + fcount + ", remark = " + remark + "}";
    }
}
