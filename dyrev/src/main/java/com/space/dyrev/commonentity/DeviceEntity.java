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
 *                                                                                             @Date: 2018/10/16 17:57
 *                                                                                             @Description: 
 **/
@Entity
@Table(name = "t_device")
public class DeviceEntity {

    @Id
    @GeneratedValue
    private Integer id;


    /*************************注册前数据*********************/

    // uuid imei
    private String uuid;

    // openudid cd5deef67704a09e
    // 这个也是adid
    private String openudid;

    // 090be04d-475c-40ab-a229-f02f4b02214d
    private String clientudid;

    // 设备type
    private String deviceType;

    // 设备平台
    private String devicePlatform = "android";

    // 设备品牌
    private String deviceBrand;

    // sim卡号码(暂时不弄)
    private String simICCid = "";

    // sim卡随机生成 460078015808988
    private String imsi;

    // 谷歌广告id   cd5deef67704a09e
    private String adid;

    private String resolution;

    private String dpi;

    private String cpuAbi = "armeabi-v7a";


    // 和serial_number相同，可能随机生成的，格式：6d16cfb7d440
    private String buildSerial;

    private String mc;

    // MIUI-8.9.13
    private String rom = "MIUI-8.9.13";

    // miui_V10_8.9.13
    private String romVersion = "miui_V10_8.9.13";



    /*************************注册后数据*********************/
    // 设备id，did
    private String deviceId;

    // 安装id，iid
    private String installId;

    // 设备cookie
    @Column(columnDefinition = "text")
    private String deviceCookies;

    private String xttlogid;



    /**
     * 构造函数
     */
    public DeviceEntity(){

    }

//    /**
//     * 构造函数，注册设备前
//     * @param uuid
//     * @param openudid
//     */
//    public DeviceEntity(String uuid, String openudid) {
//        this.uuid = uuid;
//        this.openudid = openudid;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUuid() {
        return uuid;
    }

    public String getImei() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOpenudid() {
        return openudid;
    }

    public String getAdid() {
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

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId;
    }

    public String getDeviceCookies() {
        return deviceCookies;
    }

    public void setDeviceCookies(String deviceCookies) {
        this.deviceCookies = deviceCookies;
    }

    public JSONObject getDeviceCookiesJSON() {

        return JSONObject.parseObject(deviceCookies);
    }


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getSimICCid() {
        return simICCid;
    }

    public void setSimICCid(String simICCid) {
        this.simICCid = simICCid;
    }


    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getCpuAbi() {
        return cpuAbi;
    }

    public void setCpuAbi(String cpuAbi) {
        this.cpuAbi = cpuAbi;
    }

    public String getBuildSerial() {
        return buildSerial;
    }

    public void setBuildSerial(String buildSerial) {
        this.buildSerial = buildSerial;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getRomVersion() {
        return romVersion;
    }

    public void setRomVersion(String romVersion) {
        this.romVersion = romVersion;
    }

    public String getClientudid() {
        return clientudid;
    }

    public void setClientudid(String clientudid) {
        this.clientudid = clientudid;
    }

    public String getXttlogid() {
        return xttlogid;
    }

    public void setXttlogid(String xttlogid) {
        this.xttlogid = xttlogid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", openudid='" + openudid + '\'' +
                ", clientudid='" + clientudid + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", devicePlatform='" + devicePlatform + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", simICCid='" + simICCid + '\'' +
                ", imsi='" + imsi + '\'' +
                ", adid='" + adid + '\'' +
                ", resolution='" + resolution + '\'' +
                ", dpi='" + dpi + '\'' +
                ", cpuAbi='" + cpuAbi + '\'' +
                ", buildSerial='" + buildSerial + '\'' +
                ", mc='" + mc + '\'' +
                ", rom='" + rom + '\'' +
                ", romVersion='" + romVersion + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", installId='" + installId + '\'' +
                ", deviceCookies='" + deviceCookies + '\'' +
                ", xttlogid='" + xttlogid + '\'' +
                '}';
    }
}
