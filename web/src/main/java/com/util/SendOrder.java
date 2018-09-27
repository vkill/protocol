package com.util;

import com.space.entity.WebOrderEntity;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
        String params = "orderNumber=" + entity.getOrderNumber() + "&videoID="+entity.getVideoId() + "&thumbUpOrFollowNum=" + entity.getOperaCount()+"&status=1" +"&types="+entity.getGoodsType();
        String url = HOST + API + params;
//        System.out.println(url);
        String result = "";
        try {
            Document document = Jsoup.connect(url).timeout(3000).get();
//            System.out.println(document.body().text());
            result = document.body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(result);
        if (result.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据url获取responseHeader里面的Location
     * @param getUrl
     * @return
     */
    public static String getVideoId(String getUrl) {
        String result = "";
        try {
            URL url = new URL(getUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            String code = connection.getResponseMessage();
            int responseCode = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            //获取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                if (line.contains("/share/video")) {
                    int start = line.indexOf("/share/video");
                    result = line.substring(start + 13, start + 13+ 19);
                    System.out.println(result);
                    System.out.println(start);
                    System.out.println(line);
                }
            }
            reader.close();
            //该干的都干完了,记得把连接断了
             connection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        getVideoId("http://v.douyin.com/dVWwDQ/ ");
//    }

}
