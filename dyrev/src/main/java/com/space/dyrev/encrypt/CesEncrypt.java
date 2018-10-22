package com.space.dyrev.encrypt;

import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.util.formatutil.ScaleTrans;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
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

    private static final String HOST = "192.168.0.100";

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


//    public static void main(String[] args) {
//        String base64 = "eyJwMSI6IjU4MzA2MjE3NzkyIiwicDIiOiI0Njc3Nzg3OTUzMyIsInBrZyI6ImNvbS5zcy5hbmRyb2lkLnVnYy5hd2VtZSIsImZwIjoiWGlhb21pXC9zYW50b25pXC9zYW50b25pOjYuMC4xXC9NTUIyOU1cLzcuNS4xOTp1c2VyXC9yZWxlYXNlLWtleXMiLCJ2YyI6MjcwLCJWUE4iOjAsIndpZmltYWMiOiJkYzpmZToxODo2MjpmMjpmNSIsImxvY2F0aW9uIjoiIiwiYXBwcyI6W10sImh3Ijp7ImJyYW5kIjoiWGlhb21pIiwibW9kZWwiOiJSZWRtaSA0WCIsImJvYXJkIjoiUUNfUmVmZXJlbmNlX1Bob25lIiwiZGV2aWNlIjoic2FudG9uaSIsInByb2R1Y3QiOiJzYW50b25pIiwiYnQiOiJ1bmtub3duIiwiZGlzcGxheSI6IjcyMCoxMjgwIiwiZHBpIjozMjAsImJhdCI6NDEwMCwiY3B1Ijp7Imh3IjoiUXVhbGNvbW0gVGVjaG5vbG9naWVzLCBJbmMgTVNNODk0MCIsIm1heCI6IjE0MDEwMDAiLCJtaW4iOiI5NjAwMDAiLCJmdCI6ImhhbGYgdGh1bWIgZmFzdG11bHQgdmZwIGVkc3AgbmVvbiB2ZnB2MyB0bHMgdmZwdjQgaWRpdmEgaWRpdnQgbHBhZSBldnRzdHJtIGFlcyBwbXVsbCBzaGExIHNoYTIgY3JjMzIifSwibWVtIjp7InJhbSI6IjIuNzYgR0IiLCJyb20iOiIyNC4wOCBHQiIsInNkIjoiMjQuMDggR0IifX0sImlkIjp7ImltZWkiOiI4NjY3MDkwMzM0Njc4NzgiLCJpbXNpIjoiNDYwMDExNTEyNDgwNzMyIiwiYWRpZCI6IjNlMDU5MzFlZWMxYTkwYWYiLCJtYWMiOiJmNDpmNTpkYjoxOTo2ZjpkMCIsInNlcmlhbCI6IjZjZTMyMmU3ZDQ0MCJ9LCJlbXVsYXRvciI6eyJzaWciOjAsImNiIjoxMCwiZmlsZSI6W10sInByb3AiOltdfSwiZW52Ijp7InZlciI6IjAuNS44LjI4IiwicGtnIjoiY29tLnNzLmFuZHJvaWQudWdjLmF3ZW1lIiwidWlkIjoxMDExMywicmVidWlsZCI6MCwiamQiOjAsImRiZyI6MCwidGlkIjowLCJ4cGQiOjEsImhrIjpbImRsb3BlbiIsImRsc3ltIiwiZGxhZGRyIl0sInN1IjoxLCJzcCI6Ilwvc3lzdGVtXC94YmluXC9zdSIsInJvLnNlY3VyZV9zIjoiIiwicm8uZGVidWdnYWJsZV9zIjoiIiwiY2xpY2siOiIiLCJocGgiOiIxOTIuMTY4LjAuMTA4IiwiaHBwIjoiODg4OCIsIm1jIjowLCJmYyI6NTc1ODAsImpleHAiOjAsInhwb3NlZCI6MCwiY3lkaWEiOjAsImZyaWRhIjowLCJ2YXBwIjoiIiwiYXBpIjpbXX0sImV4dHJhIjoibG9naW4iLCJleHRlbnNpb24iOnsibm90aWZ5IjoxODI4OCwidHBpZDEiOjAsInRiaW4iOiIifX0=";
//        byte[] bytes = ScaleTrans.base64StringTobytes(base64);
//        String result = new String(bytes);
//        System.out.println(result);
//    }
}
