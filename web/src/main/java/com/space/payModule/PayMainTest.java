package com.space.payModule;

import com.space.entity.WebOrderEntity;
import com.space.payModule.api.EpayclientAop;
import com.space.payModule.domian.EpayclientConfig;
import com.space.payModule.domian.EpayclientRequest;
import com.space.payModule.service.PayApiService;
import com.space.payModule.service.impl.PayApiServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class PayMainTest {


    private static void makeOrder() throws Exception {
        EpayclientConfig conf 	= new EpayclientConfig();
        EpayclientRequest req 	= new EpayclientRequest();
        EpayclientAop client 	= new EpayclientAop(conf);

        req.setOrderAmount("0.01");
        req.setType("alipay");
        req.setOrderNo(getOrder());
        //请求网关
        req.setRequesturl("https://www.payonline.xin/deal.request");
        //具体回调地址
        req.setNotify("http://localhost:8888/pay/notify");

        //执行请求
        String result = client.create(req);

        //打印结果并将对应pay_url转换为图片base64位（微信手机除外）
        System.out.println("请求结果：" + result);

        //转换出的base64图编码字符串,c:/表示临时文件路径
        String img = client.getBaseimg(result,".\\QRCodeImage\\");
//        System.out.println(img);

//        System.out.println(img);


    }

    private static String getOrder() {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

        String newDate = sdf.format(new Date());

        String result="";

        Random random = new Random();

        for(int i=0;i<3;i++){

            result+=random.nextInt(10);

        }

        return newDate+result;
    }


    public static void main(String[] args) {
        PayApiService payApiService = new PayApiServiceImpl();
        WebOrderEntity webOrderEntity = new WebOrderEntity();
        webOrderEntity.setOrderNumber("2018100527348942342355643345");
        webOrderEntity.setTotalPrice(999);
        Map alipay = payApiService.makeOrderPay(webOrderEntity, "alipay");
//        System.out.println(alipay);
    }
}
