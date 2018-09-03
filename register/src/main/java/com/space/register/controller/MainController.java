package com.space.register.controller;

import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DeviceService;
import com.space.register.service.UrlRequestService;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.tools.ConstructRequestUrl;
import po.RequestTokenVo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    @Resource
    UrlRequestService urlRequestService;

    @Resource
    DeviceService deviceService;

    /**
     * 点赞模块
     * @return
     */
    @RequestMapping("/thumbsUp")
    public String thumbsUpMaker() {

        //这个是视频id，需要参数传入
        String aweme_id = "6596094673532488964";


        //获取设备信息
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(1);
        String deviceID = deviceEntity.getDeviceId();
        String deviceBrand = deviceEntity.getDevice_brand();
        String devicePlatform = deviceEntity.getDevice_platform();
        String deviceType = deviceEntity.getDevice_type();
        String iid = deviceEntity.getIid();
        String openudid = deviceEntity.getOpenudid();
        String uuid = deviceEntity.getUuid();
        String cookie = deviceEntity.getCookie();


        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);
        String host = urlRequestEntity.getHost();
        String msg = urlRequestEntity.getMessage();

        //构造token
        Map <String, String> token = new HashMap<String, String>();
        token.put("aweme_id",aweme_id);

        //type为1则代表点赞，0为取消赞
        token.put("type","1");
        token.put("retry_type","no_retry");
        token.put("iid",iid);
        token.put("device_id",deviceID);
        token.put("ac","wifi");
        token.put("channel","tengxun");
        token.put("aid","1128");
        token.put("app_name","aweme");
        token.put("version_code","176");
        token.put("version_name","1.7.6");
        token.put("device_platform",devicePlatform);
        token.put("ssmix","a");
        token.put("device_type",deviceType);
        token.put("device_brand",deviceBrand);
        token.put("language","zh");
        token.put("os_api","22");
        token.put("os_version","5.1.1");
        token.put("uuid",uuid);
        token.put("openudid",openudid);
        token.put("manifest_version_code","176");
        token.put("resolution","1280*720");
        token.put("dpi","240");
        token.put("update_version_code","1762");
        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
             ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);
        token.put("_rticket",_rticket);
        token.put("ts",ts);
        token.put("as","a1iosdfgh");
        token.put("cp","androide1");

        //url构建完成,其中cookie需要从前面的cookie参数中提取
        String url = ConstructRequestUrl.constructUrl(host, msg, token);

        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> header_cookie = new HashMap<String, String>();
        header_cookie.put("odin_tt","7e39a071b94218692995bbf2a21d2140945484838d85a1a6b3307e8d8967d611705da6385e53a4beb0da624420fedd3e");
        header_cookie.put("sid_guard","887f45fc36bc64fb2328a1bdec67bdcc%7C1535971050%7C5184000%7CFri%2C+02-Nov-2018+10%3A37%3A30+GMT");
        header_cookie.put("uid_tt","4665af8fe747caeee789fa440151b7a5");
        header_cookie.put("sid_tt","887f45fc36bc64fb2328a1bdec67bdcc");
        header_cookie.put("sessionid","887f45fc36bc64fb2328a1bdec67bdcc");

        String header_str = MapToString(header_cookie);

        header.put("Accept-Encoding","gzip");
        header.put("Cache-Control","max-stale=0");
        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
        header.put("Cookie",header_str);
        header.put("User-Agent","okhttp/3.8.1");

        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(null);
        Request request = null;
        request = ConstructRequest.constructGet(requestToSend);


        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                System.out.println("响应成功");
                System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });
        return "abc";
    }


    @RequestMapping("/follow")
    public String followMaker(){

        //这个是视频id，需要参数传入
        String user_id = "76612213462";


//        //获取设备信息
//        DeviceEntity deviceEntity = deviceService.getDeviceMsg(1);
//        String deviceID = deviceEntity.getDeviceId();
//        String deviceBrand = deviceEntity.getDevice_brand();
//        String devicePlatform = deviceEntity.getDevice_platform();
//        String deviceType = deviceEntity.getDevice_type();
//        String iid = deviceEntity.getIid();
//        String openudid = deviceEntity.getOpenudid();
//        String uuid = deviceEntity.getUuid();
//        String cookie = deviceEntity.getCookie();


        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(4);
        String host = urlRequestEntity.getHost();
        String msg = urlRequestEntity.getMessage();


        //构造token
        Map <String, String> token = new HashMap<String, String>();
        token.put("user_id",user_id);

        //type为1则代表点赞，0为取消赞
        token.put("type","1");
        token.put("retry_type","no_retry");
        token.put("iid","43081723527");
        token.put("device_id","57110710861");
        token.put("ac","wifi");
        token.put("channel","tengxun");
        token.put("aid","1128");
        token.put("app_name","aweme");
        token.put("version_code","176");
        token.put("version_name","1.7.6");
        token.put("device_platform","android");
        token.put("ssmix","a");
        token.put("device_type","oppo r11 plus");
        token.put("device_brand","oppo");
        token.put("language","zh");
        token.put("os_api","22");
        token.put("os_version","5.1.1");
        token.put("uuid","865166020352285");
        token.put("openudid","bb7da3c643ec7429");
        token.put("manifest_version_code","176");
        token.put("resolution","720*1280");
        token.put("dpi","240");
        token.put("update_version_code","1762");
        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);
        token.put("_rticket",_rticket);
        token.put("ts",ts);
        token.put("as","a1iosdfgh");
        token.put("cp","androide1");

        //url构建完成,其中cookie需要从前面的cookie参数中提取
        String url = ConstructRequestUrl.constructUrl(host, msg, token);

        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> header_cookie = new HashMap<String, String>();
        header_cookie.put("odin_tt","7e39a071b94218692995bbf2a21d2140945484838d85a1a6b3307e8d8967d611705da6385e53a4beb0da624420fedd3e");
        header_cookie.put("sid_guard","887f45fc36bc64fb2328a1bdec67bdcc%7C1535971050%7C5184000%7CFri%2C+02-Nov-2018+10%3A37%3A30+GMT");
        header_cookie.put("uid_tt","4665af8fe747caeee789fa440151b7a5");
        header_cookie.put("sid_tt","887f45fc36bc64fb2328a1bdec67bdcc");
        header_cookie.put("sessionid","887f45fc36bc64fb2328a1bdec67bdcc");

        String header_str = MapToString(header_cookie);

        header.put("Accept-Encoding","gzip");
        header.put("Cache-Control","max-stale=0");
        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
        header.put("Cookie",header_str);
        header.put("User-Agent","okhttp/3.8.1");

        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(null);
        Request request = null;
        request = ConstructRequest.constructGet(requestToSend);



        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                System.out.println("响应成功");
                System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });
        return null;
    }

    public static String MapToString(Map map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? ";" : "");
        }
        return sb.toString();
    }
}
