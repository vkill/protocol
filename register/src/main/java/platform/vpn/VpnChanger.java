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

    public static void main(String[]args) throws IOException {

        String host = "2136u62o82.imwork.net";
        int post = 1723;
        // 使用java.net.Proxy类设置代理IP
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, post));
        HttpURLConnection connection = (HttpURLConnection) new URL("http://www.baidu.com/").openConnection(proxy);
        //"Proxy-Authorization"= "Basic Base64.encode(user:password)"
        String headerKey = "Proxy-Authorization";
        String headerValue = "Basic " + "vpn46:123";
        connection.setRequestProperty(headerKey, headerValue);
        connection.setConnectTimeout(6000); // 6s
        connection.setReadTimeout(6000);
        connection.setUseCaches(false);
        System.out.println(connection.getResponseMessage());
        if(connection.getResponseCode() == 200){
            System.out.println("使用代理IP连接网络成功");
        }
    }

}
