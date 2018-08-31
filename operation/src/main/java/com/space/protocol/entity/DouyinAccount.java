package com.space.protocol.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_douyinAccount")
public class DouyinAccount {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 255)
    private String phone;

    @Column(length = 255)
    private String password;

    @Column(length = 255)
    private String sessionId;

    @Column(columnDefinition = "text")
    private String accountCookie;

    @Column(columnDefinition = "text")
    private String deviceCookie;

    @Column(length = 255)
    private String uuid;

    @Column(length = 255)
    private String openudid;

    @Column(length = 255)
    private String iid;

    @Column(length = 255)
    private String devicesId;

    @Column(length = 255)
    private String device_type;

    @Column(length = 255)
    private String device_brand;


    // 以下是随机生成的数据 可以用来真是的模拟用户操作
    @Column(length = 255)
    private String resolution;

    @Column(length = 255)
    private String os_api;

    @Column(length = 255)
    private String dpi;

    @Column(length = 30)
    private String photoArea;

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getOs_api() {
        return os_api;
    }

    public void setOs_api(String os_api) {
        this.os_api = os_api;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccountCookie() {
        return accountCookie;
    }

    public void setAccountCookie(String accountCookie) {
        this.accountCookie = accountCookie;
    }

    public String getDeviceCookie() {
        return deviceCookie;
    }

    public void setDeviceCookie(String deviceCookie) {
        this.deviceCookie = deviceCookie;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOpenudid() {
        return openudid;
    }

    public void setOpenudid(String openudid) {
        this.openudid = openudid;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_brand() {
        return device_brand;
    }

    public void setDevice_brand(String device_brand) {
        this.device_brand = device_brand;
    }
}
