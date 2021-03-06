package com.space.dyrev.commonentity;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/16 17:59
 *                                                                                             @Description: 帐号实体类
 **/
@Entity
@Table(name = "t_dy_user")
public class DyUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 帐号区域
    private String area;

    // 帐号，电话号码
    private String account;

    // 密码
    private String pwd;

    @OneToOne(optional = false)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;


    // 帐号部分相关的cookie
    @Column(columnDefinition = "text")
    private String userCookies;

    @Column(columnDefinition = "text")
    private String xTtTokenSign;


    private String sessionKey;

    private String userId;

    // 登陆的时候header返回的
    private String xTtToken;

    private String accountStatus;//账号状态 1表示账号异常 0 表示账号正常


    @Transient
    private boolean isCaptcha = false;

    @Transient
    private String captcha;

    public boolean isCaptcha() {
        return isCaptcha;
    }

    public void setCaptcha(boolean captcha) {
        isCaptcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getxTtTokenSign() {
        return xTtTokenSign;
    }

    public void setxTtTokenSign(String xTtTokenSign) {
        this.xTtTokenSign = xTtTokenSign;
    }

    public String getxTtToken() {
        return xTtToken;
    }

    public void setxTtToken(String xTtToken) {
        this.xTtToken = xTtToken;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // appLog的eventId
    private int eventId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public String getUserCookies() {
        return userCookies;
    }

    public JSONObject getUserCookiesJSON() {
        return JSONObject.parseObject(userCookies);
    }

    public void setUserCookies(String userCookies) {
        this.userCookies = userCookies;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "DyUserEntity{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", device=" + device +
                ", userCookies='" + userCookies + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", userId='" + userId + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}
