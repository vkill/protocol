package com.util;

import com.space.entity.WebOrderEntity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
//                    System.out.println(result);
//                    System.out.println(start);
//                    System.out.println(line);
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


    /**
     *根据传入视频id获取该视频的用户id
     * @param aweme_id
     * @return
     */
    public static String share(String aweme_id){

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);

        String url = "https://api.amemv.com/aweme/v1/aweme/detail/?aweme_id="+aweme_id+"&retry_type=no_retry&ac=wifi&channel=aweGW&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&manifest_version_code=176&resolution=720*1280&dpi=320&update_version_code=1762&_rticket="+_rticket+"&ts="+ts+"&as=a1iosdfgh&cp=androide1";

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host","api.amemv.com");
        header.put("Connection","Keep-Alive");
        //header.put("Cookie","install_id="+iid+";qh[360]=1;odin_tt="+odin_tt);
        header.put("User-Agent","okhttp/3.8.1");
        header.put("sdk-version","1");
        header.put("X-SS-TC","0");
        //header.put("User-Agent","com.ss.android.ugc.aweme/270 (Linux; U; Android 7.1.2; zh_CN; Redmi 4X; Build/N2G47H; Cronet/58.0.2991.0)");

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for(String key : header.keySet()){
            builder.addHeader(key, header.get(key));
        }
        Request request = builder.get().build();

        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        String result = "";
        try {
            Response response = call.execute();
            String resultLine = GzipGetteer.uncompressToString(response.body().bytes());
//            System.out.println(resultLine);
            String []temp1 = resultLine.split(", ");
            for(int i = 0;i < temp1.length;i++){
                if(temp1[i].split(":")[0].equals("\"author_user_id\"")){
                    result = temp1[i].split(":")[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] array = result.toCharArray();
        String resultToReturn = "";
        for(int i = 0;i < array.length;i++){
            if (Character.isDigit(array[i])){
                resultToReturn += array[i];
            }
        }
        return resultToReturn;
    }

    public static void main(String[] args) {
//        Date dNow = new Date( );
//        SimpleDateFormat ft =
//                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
//        String a1 = ft.format(dNow);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Date d2 = new Date();
//        String a2 = ft.format(d2);
//        System.out.println("Current Date: " + a1);
//        System.out.println("Current Date: " + a2);

        //6608775036511718659
        String share = share("6608775036511718659");
        System.out.println(share);
    }

}
