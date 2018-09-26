package com.space.register.entity;

import javax.persistence.*;

/**
 * @program: protool
 * @description: 订单存储实体类，用来存储需要点赞或者关注的订单
 * @author: Mr.gao
 * @create: 2018-09-25 15:42
 **/
@Entity
@Table(name = "t_dy_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String vedioID;

    private Double thumbUpOrFollowNum;

    private String status;

    private String types;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVedioID() {
        return vedioID;
    }

    public void setVedioID(String vedioID) {
        this.vedioID = vedioID;
    }

    public Double getThumbUpOrFollowNum() {
        return thumbUpOrFollowNum;
    }

    public void setThumbUpOrFollowNum(Double thumbUpOrFollowNum) {
        this.thumbUpOrFollowNum = thumbUpOrFollowNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
