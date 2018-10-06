package com.space.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_web_order")
/**
* @Description: 下订单的实体类
* @Author: Space
* @Date: 2018/9/27/
*/
public class WebOrderEntity {

    @Id
    @GeneratedValue
    private int id;

    // 订单号
    private String orderNumber;

    // 项目类型
    private String proType;

    // 商品类型
    private String goodsType;

    // 视频id
    private String videoId;

    // 订单份数
    private int orderCount;

    // 操作数量
    private int operaCount;

    // 订单状态 -2 未付款 -1 订单异常 1 未完成 0 完成 2 进行中
    private int orderStatus;

    // 下单时间
    private String time;

    private double totalPrice;

    // 0 已付款 1 未付款
    private int isPay;

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getOperaCount() {
        return operaCount;
    }

    public void setOperaCount(int operaCount) {
        this.operaCount = operaCount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "WebOrderEntity{" +
                "id=" + id +
                ", proType='" + proType + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", videoId='" + videoId + '\'' +
                ", orderCount=" + orderCount +
                ", operaCount=" + operaCount +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", isPay=" + isPay +
                '}';
    }
}
