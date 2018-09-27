package com.util;

import com.space.entity.WebOrderEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
* @Description: 向注册机发送订单
* @Author: Space
* @Date: 2018/9/27/
*/
public class SendOrder {

    private static String HOST = "http://192.168.0.108:8887";
    private static String API = "/order/acceptOrder?";


    /**
     * 向注册机发送订单
     * @param entity
     * @return
     */
    public static boolean sendOrder(WebOrderEntity entity) {
        String params = "orderNumber=" + entity.getOrderNumber() + "&";
        String url = HOST + API + params;
        System.out.println(url);
        try {
            Document document = Jsoup.connect(url).timeout(3000).get();
            System.out.println(document.body().text());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }
}
