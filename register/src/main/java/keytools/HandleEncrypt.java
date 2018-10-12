package keytools;

import com.mysql.cj.util.Base64Decoder;
import com.space.register.entity.DeviceEntity;
import params.CesJson;
import util.HttpTools.HttpTools;

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

    private static final String HOST = "http://crc.kitejoy.com";

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

//        System.out.println(base64params);

        // 发送请求
        String post = HttpTools.post(url, "data=" + base64params + "&type=1");

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(post);
        return decode;
    }

    public static void main(String[] args) {
        DeviceEntity deviceEntity = new DeviceEntity();
        String s = CesJson.jsonConstruct(deviceEntity);
        byte[] posts = getCes("POST", s);
        String print = new String(posts);
        System.out.println(print);

    }


}
