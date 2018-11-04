package com.space.dyrev.encrypt;

import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.testpackage.OutPutUtil;
import com.space.dyrev.util.formatutil.ScaleTrans;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
 *        @Author: space
 *        @Date: 2018/10/15 13:54
 *        @Description: xlog请求头发送的
 **/
public class CesEncrypt {

    private static final String HOST = "192.168.0.103";

    private static final String PORT = "11600";

    public enum CesEnum{
        ENCODE(1),
        DECODE(2);

        private int index;

        CesEnum(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    /**
     * 对xlog的加密数据进行加密解密
     * @param cesType ENCODE是加密，DECODE是解密
     * @param bytes 参数字节流
     * @return 结果字节流
     */
    public static byte[] cesEncrypt(CesEnum cesType, byte[] bytes) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Map<String, String> header = new HashMap();
        Map<String, String> body = new HashMap();

        String url = "http://" + HOST + ":" + PORT + "/DouYin/Ces";
        String base64Str = ScaleTrans.bytesToBase64String(bytes);
        body.put("type", String.valueOf(cesType.getIndex()));
        body.put("data", base64Str);

        RequestEntity requestEntity = new RequestEntity(RequestEnum.POST_FORM);
        requestEntity.setUrl(url);
        requestEntity.setRequestEnum(RequestEnum.POST_FORM);
        requestEntity.setOkHttpClient(okHttpClient);
        requestEntity.setHeaders(header);
        requestEntity.setBody(body);


        Response response = OkHttpTool.handleHttpReq(requestEntity);

        return ScaleTrans.base64StringTobytes(response.body().string());
    }


    public static void main(String[] args) {
        String base64 = "yQEQkfEJGAAoADIQygwNGKmmzsWKmNwCIAAoADoh";
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(base64);

        try {
            byte[] bytes = cesEncrypt(CesEnum.DECODE, decode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        OutPutUtil.printBytes(decode);

        //        byte[] bytes = ScaleTrans.base64StringTobytes(base64);
//        try {
//            byte[] bytes1 = cesEncrypt(CesEnum.DECODE, bytes);
//            String result = new String(bytes1);
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String result = new String(bytes);
//        System.out.println(result);
    }
}
