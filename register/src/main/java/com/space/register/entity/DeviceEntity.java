package com.space.register.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "t_device")
public class DeviceEntity {

    @Id
    @GeneratedValue
    private int id;

    private String device_type;

    private String device_platform;

    private String iid;

    private String uuid;

    private String openudid;

    private String deviceId;

    private String device_brand;

    @Column(columnDefinition = "text")
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    public void setDevice_platform(String device_platform) {
        this.device_platform = device_platform;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevice_brand() {
        return device_brand;
    }

    public void setDevice_brand(String device_brand) {
        this.device_brand = device_brand;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", device_type='" + device_type + '\'' +
                ", device_platform='" + device_platform + '\'' +
                ", iid='" + iid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", openudid='" + openudid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", device_brand='" + device_brand + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }

    public Map<String, String> getDeviceMap() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("device_type",device_type);
        result.put("device_platform",device_platform);
        result.put("iid",iid);
        result.put("uuid",uuid);
        result.put("openudid",openudid);
        result.put("deviceId",deviceId);
        result.put("device_brand",device_brand);
        result.put("cookie",cookie);
        return result;
    }
}
