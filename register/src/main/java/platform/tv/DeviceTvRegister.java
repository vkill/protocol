package platform.tv;

import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import org.json.JSONObject;
import params.tools.RequestURLCreater;
import platform.tcp.TcpClientForTV;

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

        String device_URL = RequestURLCreater.getUrlFromJsonAndMap(jsonObject);
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
        DeviceTvRegister deviceTvRegister =new DeviceTvRegister();
    }

}
