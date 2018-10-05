package com.space.payModule.domian;

public class EpayclientConfig {
	
	//签名方式,目前只提供MD5签名
    private String sign_type="md5";
    
    //商户APPID 具体参数请在我的App详情内应用参数查阅
    private String app_id="CTJnoM6vQG9Z54E";
    
    //商户私钥 具体参数请在我的App详情内应用参数查阅
    private String merchant_private_key="vYxP5HUtD9gjQEo";
    
    //编码格式
    private String charset="UTF-8";
    
    //最大查询重试次数
    private int MaxQueryRetry=10;
    
    //查询间隔
    private int QueryDuration=3;
    
    public String getSign() {
        return sign_type;
    }
    
    public String getAppid() {
        return app_id;
    }
    
    public String getKey() {
        return merchant_private_key;
    }
    
    public String getCharset() {
        return charset;
    }
    
    public int getMaxQuery() {
        return MaxQueryRetry;
    }
    
    public int getQueryDuration() {
        return QueryDuration;
    }
}
