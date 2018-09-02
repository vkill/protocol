package platform.tv;

import jsonreader.tools.GzipGetteer;
import jsonreader.tools.JsonTableGetter;
import okhttp3.*;
import org.json.JSONObject;
import params.tools.DeviceCreater;
import platform.tcp.TcpClientForTV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：GXDTJJC
 * @ Date       ：Created in 19:43 2018/8/30/030
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class DeviceTvRegister {

    public Request getDeviceCreaterRequest(JSONObject jsonObject){
        TcpClientForTV tcpClientForTV = new TcpClientForTV();

        String device_URL = DeviceCreater.getUrlFromJsonAndMap(jsonObject);
        String result = jsonObject.toString();
        byte[] sendMessage = GzipGetteer.compress(result);
        sendMessage = tcpClientForTV.get_Key_For_Devices(sendMessage);
        //数据准备完毕
        MediaType type=MediaType.parse("application/octet-stream");
        RequestBody fileBody= RequestBody.create(type,sendMessage);
        Request request=new Request.Builder().url(device_URL)
                .addHeader("Accept-Encoding","gzip")
                .addHeader("Cache-Control","max-stale=0")
                .addHeader("Content-Type","application/octet-stream;tt-data=a")
                .addHeader("Host","ib.snssdk.com")
                .addHeader("Connection","Keep-Alive")
                .addHeader("User-Agent","okhttp/3.7.0.6")
                .post(fileBody)//直接传输加密后的json数据
                .build();
        return request;
    }



    public static void main(String[]args) {

//        OkHttpClient mOkHttpClient =
//                new OkHttpClient.Builder()
//                        .readTimeout(60,TimeUnit.SECONDS)//设置读取超时时间
//                        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
//                        .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
//                        .build();
//        DeviceTvRegister deviceRegisterTV = new DeviceTvRegister();
//        Request request = deviceRegisterTV.getDeviceCreaterRequest();
//        Response response = null;
//        String jsonString;
//        try {
//            response = mOkHttpClient.newCall(request).execute();
//            System.out.println(GzipGetteer.uncompressToString(response.body().bytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(response.headers());
//        System.out.println("下面是请求信息");
//        System.out.println(request.toString());
//        System.out.println(request.headers());

    }

}
