package com.space.register.entity;

import javax.persistence.*;

/**
 * @program: protool
 * @description: 某应用账户信息存储类
 * @author: Mr.gao
 * @create: 2018-09-02 11:41
 **/

@Entity
@Table(name = "t_dy_user")
public class DYUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String phoneNum;

    private String phoneArea;

    private String password;

    private String simulationID;

    private String belong;

    @Column(columnDefinition = "text")

    private String userCookie;

    private String uid;

    @Column(columnDefinition = "text")
    private String app_Log;

    private int likePower;

    public int getLikePower() {
        return likePower;
    }

    public void setLikePower(int likePower) {
        this.likePower = likePower;
    }

    public String getApp_Log() {
        return app_Log;
    }

    public void setApp_Log(String app_Log) {
        this.app_Log = app_Log;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        this.phoneArea = phoneArea;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSimulationID() {
        return simulationID;
    }

    public void setSimulationID(String simulationID) {
        this.simulationID = simulationID;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getUserCookie() {
        return userCookie;
    }

    public void setUserCookie(String userCookie) {
        this.userCookie = userCookie;
    }

    @Override
    public String toString() {
        return "DYUserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", phoneArea='" + phoneArea + '\'' +
                ", password='" + password + '\'' +
                ", simulationID='" + simulationID + '\'' +
                ", belong='" + belong + '\'' +
                ", userCookie='" + userCookie + '\'' +
                '}';
    }
}
