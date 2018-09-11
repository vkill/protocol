package platform.vpn;

import java.io.IOException;
import java.net.*;

/**
 * @program: protool
 * @description: 用于更改vpn连接的实现类
 * @author: Mr.gao
 * @create: 2018-09-05 14:12
 **/
public class VpnChanger {

    public static void hehedada(){
        String host = "2136u62o82.imwork.net";
        int post = 1723;
        // 使用java.net.Proxy类设置代理IP
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, post));
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL("http://www.baidu.com/").openConnection(proxy);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //"Proxy-Authorization"= "Basic Base64.encode(user:password)"
        String headerKey = "Proxy-Authorization";
        String headerValue = "Basic " + "vpn46:123";
        connection.setRequestProperty(headerKey, headerValue);
        connection.setConnectTimeout(6000); // 6s
        connection.setReadTimeout(6000);
        connection.setUseCaches(false);

        try {
            System.out.println(connection.getResponseMessage());
            if(connection.getResponseCode() == 200){
                System.out.println("使用代理IP连接网络成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args) throws IOException {
        String result = URLDecoder.decode("https://is.snssdk.com/api/ad/splash/aweme/v14/?_unused=0&carrier=%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8&mcc_mnc=46000&ad_area=1080x1854&os_api=22&device_platform=android&os_version=5.1&display_density=1080x1920&resolution=1920x1080&dpi=480&language=zh&device_brand=Meizu&device_type=MX5&display_dpi=480&density=3.0&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&update_version_code=1762&version_code=176&version_name=1.7.6&manifest_version_code=1.7.6&iid=43717888625&device_id=41336725255&openudid=ef8ad7929c2e0994&retry_type=no_retry&iid=43717888625&device_id=41336725255&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type=MX5&device_brand=Meizu&language=zh&os_api=22&os_version=5.1&uuid=867246022383583&openudid=ef8ad7929c2e0994&manifest_version_code=176&resolution=1080*1920&dpi=480&update_version_code=1762&_rticket=1536551097562&ts=1536551097");
        System.out.println(result);
    }

}
