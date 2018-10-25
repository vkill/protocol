package com.space.dyrev.util.httputil;



import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.util.httpiputil.XDaiLiApiUtil;
import okhttp3.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
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
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/19 17:22
 *                                                                                             @Description: OKHttp的封装类
 **/
public class OkHttpTool {

    /**
     * 获取某个okhttp代理
     * @return
     */
    public static OkHttpClient getOkhttpClient(OkhttpType okhttpType) {
        if (okhttpType == OkhttpType.PROXY) {
            return new OkHttpClient.Builder().proxy(XDaiLiApiUtil.getOneIpAndPort()).build();
        } else {
            return new OkHttpClient();
        }
    }

    /**
     * 根据ip和端口获得一个
     * @param host
     * @param port
     * @return
     */
    public static OkHttpClient getOkhttpClient(String host, int port) {
        Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(host, port));
        return new OkHttpClient.Builder().proxy(proxy).build();
    }

    /**
     * 处理OkHttp请求
     * @param res
     * @return response
     **/
    public static Response handleHttpReq(RequestEntity res) throws IOException {
        RequestEnum requestEnum = res.getRequestEnum();
        Response response = null;
        switch (requestEnum) {
            case GET:
                response = get(res.getOkHttpClient(), res.getUrl(), res.getHeaders());
                break;
            case POST_OCT:
                response = post(res.getOkHttpClient(), res.getUrl(), res.getHeaders(),res.getBytesBody(), res.getBytesType());
                break;
            case POST_FORM:
                response = post(res.getOkHttpClient(), res.getUrl(), res.getHeaders(), res.getBody());
                break;
        }
        return response;
    }



    /**
     * 发送二进制流的okhttp请求
     * @param url
     * @param header
     * @param bytes
     */
    public static Response post(OkHttpClient okHttpClient, String url, Map<String, String> header, byte[] bytes, String byteType) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        MediaType type = null;
        if (byteType.equals("json")) {
            type = MediaType.parse("application/json");
        } else {
            type = MediaType.parse("application/octet-stream");
        }
        RequestBody requestBody = RequestBody.create(type, bytes);

        for(String key : header.keySet()){
            builder.addHeader(key, header.get(key));
        }

        Request request = builder.post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;

    }

    /**
     * 发送表格的post请求
     * @param okHttpClient
     * @param url
     * @param header
     * @param body
     * @return
     * @throws IOException
     */
    public static Response post(OkHttpClient okHttpClient, String url, Map<String, String> header, Map<String, String> body) throws IOException {
        // 构建POST请求，并设置请求消息头
        //requestEntity中包含三部分，Url、Header和Body
        FormBody.Builder formBody = new FormBody.Builder();     //创建表单请求体
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for(String key : header.keySet()){        //添加header信息
            builder.addHeader(key, header.get(key));
        }

        for(String key : body.keySet()){      //添加body信息
//            builder.post(formBody.build()).build();
            formBody.add(key, body.get(key));
        }
        Request request = builder.post(formBody.build()).build();

        Call call = okHttpClient.newCall(request);
        return call.execute();

    }


    /**
     * 发送表格的get请求
     * @param okHttpClient
     * @param url
     * @param header
     * @return
     * @throws IOException
     */
    public static Response get(OkHttpClient okHttpClient, String url, Map<String, String> header) throws IOException {
        // 构建GET请求，并设置请求消息头
        //requestEntity中包含两部分，Url、Header
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (header != null) {
            for(String key : header.keySet()){        //添加header信息
                builder.addHeader(key, header.get(key).trim());
            }
        }
        Request request = builder.get().build();
        Call call = okHttpClient.newCall(request);
        return call.execute();

    }

}
