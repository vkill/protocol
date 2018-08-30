package com.space.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 255)
    private String makeOrderDate;

    @Column(length = 255)
    private String projectType;

    @Column(length = 255)
    private String operationType;

    private  int count; // 点赞数

    private double price;

    private double totalPrice;

    private boolean isFinish;

    @Column(length = 255)
    private String operationData;

    @Column(length = 255)
    private String userSign;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public String getMakeOrderDate() {
        return makeOrderDate;
    }

    public void setMakeOrderDate(String makeOrderDate) {
        this.makeOrderDate = makeOrderDate;
    }

    public String getOperationData() {
        return operationData;
    }

    public void setOperationData(String operationData) {
        this.operationData = operationData;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }
}
