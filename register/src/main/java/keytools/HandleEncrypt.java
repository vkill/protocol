package keytools;

import com.mysql.cj.util.Base64Decoder;
import com.space.register.entity.DeviceEntity;
import params.CesJson;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import util.HttpTools.HttpTools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                                                                             @ClassName HandleEncrypt
 *                                                                                             @Author: space
 *                                                                                             @Description 解决Ces加密， 调用
 *                                                                                             @Date: 2018/10/11 23:29
 **/
public class HandleEncrypt {

    private static final String HOST = "http://192.168.0.105";

    private static final String PORT = "11600";

     /**
      * Ces的加密
      * @param type POST还是GET
      * @param params json字符串
      * @return
      */
    public static byte[] getCes(String type, String params) {
        String url = HOST + ":" + PORT + "/DouYin/Ces";
        Base64.Encoder encoder = Base64.getEncoder();
        String base64params = encoder.encodeToString(params.getBytes());
        System.out.println(base64params);
//        System.out.println(base64params);

        // 发送请求 type=1加密  type=2解密
        String post = HttpTools.post(url, "data=" + base64params + "&type=" + type);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(post);
        return decode;
    }


    public static void main(String[] args) {
        DeviceEntity deviceEntity = new DeviceEntity();
        String s = CesJson.jsonConstruct(deviceEntity);
        String base64 = "AbDACQJspFm+ZAojQvWMSHW+RM7OZcbGrs4=";
        //下面解密
        String url = HOST + ":" + PORT + "/DouYin/Ces";
        String test1 = HttpTools.post(url, "data=" + base64 + "&type=2");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(test1);
        System.out.println(new String(decode));




    }


}
