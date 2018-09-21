package com.space.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_order_type")
public class OrderType {

    @Id
    @GeneratedValue
    private int id;

    // 类型标识
    private String typeSign;

    // 类型描述
    private String typeDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeSign() {
        return typeSign;
    }

    public void setTypeSign(String typeSign) {
        this.typeSign = typeSign;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}
