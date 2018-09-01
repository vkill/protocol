package platform.tv;

import jsonreader.tools.GzipGetteer;
import jsonreader.tools.JsonTableGetter;
import okhttp3.*;
import org.json.JSONObject;
import params.DeviceCreater;
import platform.tcp.TcpClientForTV;

import java.io.IOException;

/**
 * @ Author     ：GXDTJJC
 * @ Date       ：Created in 19:43 2018/8/30/030
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class DeviceRegisterTV {

    public Request testDeviceGetter(){

        TcpClientForTV tcpClientForTV = new TcpClientForTV();
        MediaType type=MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"
        JSONObject jsonObject = JsonTableGetter.construtJson();
        //System.out.println(jsonObject);
        String device_URL = DeviceCreater.getUrlFromJsonAndMap(jsonObject);
        //System.out.println(device_URL);
        String result = jsonObject.toString();
        byte[] sendMessage = GzipGetteer.compress(result);
        sendMessage = tcpClientForTV.get_Key_For_Devices(sendMessage);
        RequestBody fileBody= RequestBody.create(type,sendMessage);
        RequestBody multiPartBody = new MultipartBody.Builder()
                .setType(MultipartBody.ALTERNATIVE)
                .addPart(fileBody)
                .build();

        FormBody.Builder params=new FormBody.Builder();

        Request request=new Request.Builder().url(device_URL)
                .addHeader("Accept-Encoding","gzip")
                .addHeader("Cache-Control","max-stale=0")
                .addHeader("Content-Type","application/octet-stream;tt-data=a")
                .addHeader("Content-Length","676")
                .addHeader("Host","ib.snssdk.com")
                .addHeader("Connection","Keep-Alive")
                .addHeader("User-Agent","okhttp/3.8.1")
                .post(multiPartBody)//传参数、文件或者混合，改一下就行请求体就行
                .build();

        return request;
    }


    public static void main(String[]args) {
        OkHttpClient client = new OkHttpClient();
        DeviceRegisterTV deviceRegisterTV = new DeviceRegisterTV();
        Request request = deviceRegisterTV.testDeviceGetter();
        Response response = null;

        try {
            response = client.newCall(request).execute();
            String jsonString = response.body().string();
            //System.out.println(GzipGetteer.uncompressToString(response.body().bytes(), "utf-8"));
            System.out.println(jsonString+"   输出了");

            System.out.println(request.body().contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(request.url());
        System.out.println(request.body().contentType());
    }

}
