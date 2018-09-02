package platform.tv;

import jsonreader.tools.GzipGetteer;
import jsonreader.tools.JsonTableGetter;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import params.DeviceCreater;
import platform.tcp.TcpClientForTV;
import util.SslUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        JSONObject jsonObject = null;
        jsonObject = JsonTableGetter.construtJson();
        System.out.println(jsonObject);
        String device_URL = DeviceCreater.getUrlFromJsonAndMap(jsonObject);
        //String url_buff = "https://ib.snssdk.com/service/2/device_register/?device_id=39262138748&ac=wifi&channel=baidu&aid=1128&app_name=aweme&version_code=183&version_name=1.8.3&device_platform=android&ssmix=a&device_type=f103&device_brand=gionee&language=zh&os_api=22&os_version=5.1.1&uuid=865166024287115&openudid=06e7aa24d4d4c8a4&manifest_version_code=183&resolution=900*1600&dpi=240&update_version_code=1832&_rticket=1535795857687&tt_data=a";
        System.out.println(jsonObject.toString());
        //String result = "{\"magic_tag\":\"ss_app_log\",\"header\":{\"display_name\":\"抖音短视频\",\"update_version_code\":1832,\"manifest_version_code\":183,\"aid\":1128,\"channel\":\"baidu\",\"appkey\":\"57bfa27c67e58e7d920028d3\",\"package\":\"com.ss.android.ugc.aweme\",\"app_version\":\"1.8.3\",\"version_code\":183,\"sdk_version\":201,\"os\":\"Android\",\"os_version\":\"5.1.1\",\"os_api\":22,\"device_model\":\"f103\",\"device_brand\":\"gionee\",\"device_manufacturer\":\"gionee\",\"cpu_abi\":\"armeabi-v7a\",\"build_serial\":\"00c7c516\",\"release_build\":\"61b8304_20180522\",\"density_dpi\":240,\"display_density\":\"hdpi\",\"resolution\":\"1600x900\",\"language\":\"zh\",\"mc\":\"00:81:41:de:1e:56\",\"timezone\":8,\"access\":\"wifi\",\"not_request_sender\":0,\"carrier\":\"CHINA MOBILE\",\"mcc_mnc\":\"46000\",\"rom\":\"V9.5.2.0.LACCNFA\",\"rom_version\":\"LMY48Z\",\"sig_hash\":\"aea615ab910015038f73c47e45d21466\",\"device_id\":\"39262138748\",\"openudid\":\"06e7aa24d4d4c8a4\",\"udid\":\"865166024287115\",\"clientudid\":\"71cfb1e8-ca28-447b-9acf-0b5c4033cb88\",\"serial_number\":\"00c7c516\",\"sim_serial_number\":[],\"region\":\"CN\",\"tz_name\":\"Asia\\/Shanghai\",\"tz_offset\":28800,\"sim_region\":\"cn\"},\"_gen_time\":1535795857683}";
        String result = jsonObject.toString();
        System.out.println(result);
        byte[] sendMessage = GzipGetteer.compress(result);
        sendMessage = tcpClientForTV.get_Key_For_Devices(sendMessage);
        //数据准备完毕
        MediaType type=MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"

        RequestBody fileBody= RequestBody.create(type,sendMessage);
        RequestBody multiPartBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addPart(fileBody)
                .build();

        Request request=new Request.Builder().url(device_URL)
                .addHeader("Accept-Encoding","gzip")
                .addHeader("Cache-Control","max-stale=0")
                .addHeader("Content-Type","application/octet-stream;tt-data=a")
                .addHeader("Content-Length","692")
                .addHeader("Host","ib.snssdk.com")
                .addHeader("Connection","Keep-Alive")
                .addHeader("User-Agent","okhttp/3.7.0.6")
                .post(fileBody)//传参数、文件或者混合，改一下就行请求体就行
                .build();

        return request;
    }


    public static void main(String[]args) {
//        System.setProperty("https.proxyHost", "192.168.0.110");
//        System.setProperty("https.proxyPort", "8899");
//        System.setProperty("javax.Net.ssl.trustStore", "C:\\Program Files\\Java\\jre-10.0.2\\lib\\security\\FiddlerRoot.cer");
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("靠 这都不行");
//        }
        OkHttpClient mOkHttpClient =
                new OkHttpClient.Builder()
                        .readTimeout(60,TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                        .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
                        .build();
        DeviceRegisterTV deviceRegisterTV = new DeviceRegisterTV();
        Request request = deviceRegisterTV.testDeviceGetter();
        Response response = null;
        String jsonString;
        try {
            SslUtils.ignoreSsl();
            response = mOkHttpClient.newCall(request).execute();
//            jsonString = response.body().string();
//
//            System.out.println(jsonString);
//
//            System.out.println(request.body().contentLength());
            System.out.println(GzipGetteer.uncompressToString(response.body().bytes()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response.headers());
        System.out.println("下面是请求信息");
        System.out.println(request.toString());
        System.out.println(request.headers());

    }

}
