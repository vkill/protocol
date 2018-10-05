package com.space.payModule.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.space.payModule.domian.EpayclientConfig;
import com.space.payModule.domian.EpayclientRequest;
import com.space.payModule.payUtil.HttpUtils;
import com.space.payModule.payUtil.MD5Utils;
import com.space.payModule.payUtil.QRCodeUtil;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EpayclientAop {
	
	private EpayclientConfig config;
	
	public EpayclientAop(EpayclientConfig conf) {
		
		this.config = conf;
	}
	
//	public String log(EpayclientRequest request) {
//		
//		System.out.println("log");
//        Map<String,String> params = new IdentityHashMap<String, String>();
//        
//        params.put("app_id",config.getAppid());
//        params.put("charset",config.getCharset());
//        params.put("order_no",request.getOrderNo());
//        params.put("sign",this.getSign(this.getParam(request)));
//        
//        StringBuilder res = new StringBuilder();
//        
//        for(Map.Entry<String,String> entry:params.entrySet()){
//            res.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//        }
//        
//        String result = HttpUtils.doPost(request.getRequesturl(),res.toString());
//        
//        
//        return result;
//	}
	
    public String create(EpayclientRequest request)  {
    	
//    	System.out.println("create");
        Map<String,String> params = new IdentityHashMap<String, String>();
        
        params.put("app_id",config.getAppid());
        params.put("order_amount",request.getOrderAmount());
        params.put("charset",config.getCharset());
        params.put("order_no",request.getOrderNo());
        params.put("notify",request.getNotify());
        params.put("type",request.getType());
        params.put("mobile",request.getMobile());
        params.put("subject",request.getSubject());
        params.put("time_expire","5m");
        params.put("sign",this.getSign(this.getParam(request)));
        
        StringBuilder res = new StringBuilder();
        
        for(Map.Entry<String, String> entry:params.entrySet()){
            res.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        System.out.println("res:" + res.toString());
        String result = HttpUtils.doPost(request.getRequesturl(),res.toString());
        
        return result;
    }
    
    public String wxMobilepay(EpayclientRequest request) {
    	
    	System.out.println("wxMobilepay");
        Map<String,String> params = new IdentityHashMap<String, String>();
        
        params.put("app_id",config.getAppid());
        params.put("order_amount",request.getOrderAmount());
        params.put("charset",config.getCharset());
        params.put("order_no",request.getOrderNo());
        params.put("notify",request.getNotify());
        params.put("type",request.getType());
        params.put("mobile",request.getMobile());
        params.put("subject",request.getSubject());
        params.put("time_expire","5m");
        params.put("sign",this.getSign(this.getParam(request)));
        
        StringBuilder res = new StringBuilder();
        
        for(Map.Entry<String, String> entry:params.entrySet()){
            res.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        
    	return "https://www.payonline.xin/create.request?"+res.toString();
    }
    
    public String zfbMobilepay(String result) {
    	
    	System.out.println("zfbMobilepay");
    	JSONObject jobj = JSON.parseObject(result);
    	
    	String URL = jobj.getString("pay_url");
    	
    	return URL;
    }
    
    public String getBaseimg(String result, String path) throws Exception {
    	
    	System.out.println("getBaseimg");
    	JSONObject jobj = JSON.parseObject(result);
    	
    	String imgURL = jobj.getString("pay_url");
    	
    	QRCodeUtil.encode(imgURL,"",path,true);
    	
        String imgFile = path+"tmp.jpg";
        
        InputStream in = null;
        byte[] data = null;
        
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串

    }
    
    //create中调用
    private Map<String,String> getParam(EpayclientRequest req){
    	System.out.println("getParam");
        Map<String,String> params = new LinkedHashMap<String, String>();
        params.put("app_id",config.getAppid());
        params.put("charset",config.getCharset());
        params.put("order_amount",req.getOrderAmount());
        params.put("order_no",req.getOrderNo());
        params.put("sign_type",config.getSign());
        return params;
    }

    //sign中调用
    private String getSign(Map<String,String> params){
    	System.out.println("getSign");
        StringBuilder res = new StringBuilder();
        for(Map.Entry<String, String> entry:params.entrySet()){
            res.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        res.append("key=").append(config.getKey());
        
        return MD5Utils.md5((res.toString()));
    }
}
