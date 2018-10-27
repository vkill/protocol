package com.space.dyrev.commonentity;

import com.space.dyrev.enumeration.PhoneArea;

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
 *                                
 *        @Author: space
 *        @Date: 2018/10/22 20:45
 *        @Description: 
 **/
public class PhoneEntity {

    private String pid;

    private String time;

    private String comNum;

    private PhoneArea area;

    private String phoneNum;

    private String code;


    public PhoneEntity() {
    }

    //OK                    |P_ID       |获取时间       |串口号        |手机号    |发送短信项目的接收号码    |国家名称或区号
    public PhoneEntity(String pid, String time, String comNum, String phoneNum, String areaNum) {
        this.pid = pid;
        this.time = time;
        this.comNum = comNum;
        this.phoneNum = phoneNum;
        if (areaNum.equals("+86")) {
            this.area = PhoneArea.CHINA;
        }


    }



    public PhoneEntity(PhoneArea area, String phoneNum) {
        this.area = area;
        this.phoneNum = phoneNum;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComNum() {
        return comNum;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PhoneArea getArea() {
        return area;
    }

    public void setArea(PhoneArea area) {
        this.area = area;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "pid='" + pid + '\'' +
                ", time='" + time + '\'' +
                ", comNum='" + comNum + '\'' +
                ", area=" + area +
                ", phoneNum='" + phoneNum + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
