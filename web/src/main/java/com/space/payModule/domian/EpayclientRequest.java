package com.space.payModule.domian;

public class EpayclientRequest {
	
    private String requesturl="https://www.payonline.xin/deal.request";		//请求网关
    private String notify;		//回调地址，请保持和应用配置内的响应地址一致
    private String orderNo;		//订单号
    private String subject;		//商品名称
    private String orderAmount;	//价格
    private String type="zfb";	//请求支付类型
    private String mobile="0";	//是否手机支付

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getRequesturl() {
        return requesturl;
    }

    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}
