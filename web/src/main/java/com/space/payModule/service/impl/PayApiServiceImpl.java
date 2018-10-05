package com.space.payModule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.space.entity.WebOrderEntity;
import com.space.payModule.api.EpayclientAop;
import com.space.payModule.domian.EpayclientConfig;
import com.space.payModule.domian.EpayclientRequest;
import com.space.payModule.service.PayApiService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @Description: 支付实现
* @Author: Space
* @Date: 2018/10/5
*/
@Service("payApiService")
public class PayApiServiceImpl implements PayApiService {

    // 回调通知地址
    private static final String NOTIFY_URL = "http://localhost:8888/pay/notify";

    @Override
    public Map makeOrderPay(WebOrderEntity webOrderEntity, String payType) {
        Map result = new HashMap();

        EpayclientConfig conf 	= new EpayclientConfig();
        EpayclientRequest req 	= new EpayclientRequest();
        EpayclientAop client 	= new EpayclientAop(conf);

        // 订单号
        req.setOrderNo(webOrderEntity.getOrderNumber());

        // 设置支付类型
        req.setType(payType);

        req.setSubject(webOrderEntity.getGoodsType() + " * " + webOrderEntity.getOrderCount() + "个");

        // 设置订单金额
        req.setOrderAmount(Double.toString(webOrderEntity.getTotalPrice()));


        // 请求网关
        req.setRequesturl("https://www.payonline.xin/deal.request");

        // 具体回调地址
        req.setNotify(NOTIFY_URL);

        // 请求结果
        String resultString = client.create(req);

        // 结果转成json
        JSONObject jsonResult = JSONObject.parseObject(resultString);

        // 出错
        if (jsonResult.containsKey("err")) {
            result.put("status","1");
            result.put("msg",jsonResult.getString("msg"));
        }

        System.out.println("json结果: " +jsonResult);

        // 正常
        if (jsonResult.getString("state")!=null && jsonResult.getString("state").equals("SUCCESS")) {
            String orderNumber = jsonResult.getString("order_no");
            try {
                // 转换出的base64图编码字符串,表示临时文件路径
                String QRCode = client.getBaseimg(resultString, ".\\QRCodeImage\\");
                result.put("status","0");
                result.put("order_no",orderNumber);
                result.put("order_qrcode", QRCode);
            } catch (Exception e) {
                result.put("status","1");
                result.put("msg","二维码生成失败");
                e.printStackTrace();
            }
        } else {
            result.put("status","1");
            result.put("msg","二维码生成失败");
        }
        return result;
    }
//    private static void makeOrder() throws Exception {
//        EpayclientConfig conf 	= new EpayclientConfig();
//        EpayclientRequest req 	= new EpayclientRequest();
//        EpayclientAop client 	= new EpayclientAop(conf);
//
//        req.setOrderAmount("0.01");
//        req.setType("alipay");
//        req.setOrderNo(getOrder());
//        //请求网关
//        req.setRequesturl("https://www.payonline.xin/deal.request");
//        //具体回调地址
//        req.setNotify("http://localhost:8888/pay/notify");
//
//        //执行请求
//        String result = client.create(req);
//
//        //打印结果并将对应pay_url转换为图片base64位（微信手机除外）
//        System.out.println("请求结果：" + result);
//
//        //转换出的base64图编码字符串,c:/表示临时文件路径
//        String img = client.getBaseimg(result,".\\QRCodeImage\\");
////        System.out.println(img);
//
////        System.out.println(img);
//    }
}
