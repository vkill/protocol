package com.space.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_order_type")
public class OrderType {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 255)
    private String projectType; // 项目类别

    @Column(length = 255)
    private String operationType; // 操作类型

    private double price; // 价格，浮动变化，单位：个数/元

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
