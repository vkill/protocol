package platform.tv;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @program: protocol
 * @description: 用于发送http请求的实现类
 * @author: gaoxiang
 * @create: 2018-08-27 15:07
 **/
public class OKHttpTest {

    public static Request firstInfo() throws IOException {
       // 创建HTTP客户端
       OkHttpClient client = new OkHttpClient.Builder().build();

       // 构建GET请求，并设置User-Agent请求消息头和Cookie请求消息头
       Request request = new Request.Builder()
               .url("https://is.snssdk.com/passport/mobile/send_code/v1/?mix_mode=1&mobile=2e3d3325343d3d25353034302533303235&captcha=&scene=0&type=3731&unbind_exist=0&ticket=&retry_type=no_retry&iid=42270355996&device_id=53513398877&ac=wifi&channel=xiaomi&aid=1128&app_name=aweme&version_code=230&version_name=2.3.0&device_platform=android&ssmix=a&device_type=vivo+x5pro+d&device_brand=vivo&language=zh&os_api=22&os_version=5.1.1&uuid=865166022589744&openudid=1b9e8fa3677caab5&manifest_version_code=230&resolution=720*1280&dpi=240&update_version_code=2302&_rticket=1535340100236&ts=1535340099&as=a1iosdfgh&cp=androide1")
               .addHeader("Host", "is.snssdk.com")
               .addHeader("Connection", "keep-alive")
               .addHeader("Cookie", "install_id=42270355996; ttreq=1$1d84f4cffafe709488a646eedb37d563eb65bc8d; odin_tt=b9a4aa751b13b41580e266d0cdecf317a54cde8db81711b7c4ac61e34b75ef50a257b59761731ec5da13b7c3072d5245; qh[360]=1")
               .addHeader("Accept-Encoding", "gzip")
               .addHeader("X-SS-REQ-TICKET", "1535340100244")
               .addHeader("X-SS-TC", "0")
               .addHeader("User-Agent", "com.ss.android.ugc.aweme/230 (Linux; U; Android 5.1.1; zh_CN; vivo x5pro d; Build/LMY47I; Cronet/58.0.2991.0)")
               .get()
               .build();

       /**
        // 执行GET请求，并在异步回调中处理请求
       Response response = client.newCall(request).execute();

        // 打印登录用户名，验证是否获取到了用户的登录信息(状态信息)
       if (response.isSuccessful()) {
           String content = response.body().string();
           System.out.println(content);
       }
        **/
       return request;
    }

    public static void main(String[]args) throws IOException {
        Request request = firstInfo();
        System.out.println(request.toString());
        System.out.println();
        System.out.println(request.body().toString());
        System.out.println(request.headers());
        System.out.println();
        System.out.println(request.body());
    }

}
