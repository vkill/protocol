package com.space.dyrev.util.httpiputil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

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
 *                                
 *        @Author: space
 *        @Date: 2018/10/25 15:43
 *        @Description: 迅代理api
 **/
public class XDaiLiApiUtil {

    private static final Logger logger = LoggerFactory.getLogger(XDaiLiApiUtil.class);

    //http://api.xdaili.cn/xdaili-api//privateProxy/applyStaticProxy?spiderId=8d58e76da82e431cb6021e32d1b875c3&returnType=2&count=1
    private static final String HOST = "api.xdaili.cn";

    private static final String FUNC = "/xdaili-api//privateProxy/applyStaticProxy?";

    private static final String SPIDER_Id = "8d58e76da82e431cb6021e32d1b875c3";

    private static final int RETURN_TYPE = 2;

    private static final int COUNT = 1;

    /**
     * 获取一个ip
     * @return
     */
    public static Proxy getOneIpAndPort() {
        return api().get(0);
    }

    /**
     * 根据数量获取ip
     * @param count
     * @return
     */
    public static List<Proxy> getProxyList(int count) {
        return api();
    }

    /**
     * 获取5个ip的api
     * @return
     */
    private static List<Proxy> api() {
        List<Proxy> list = new ArrayList<>();
        StringBuffer url = new StringBuffer("http://" + HOST + FUNC);
        url.append("spiderId="+SPIDER_Id);
        url.append("&returnType=" + RETURN_TYPE);
        url.append("&count=" + COUNT);
        String urls = url.toString();

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestEntity req = new RequestEntity(RequestEnum.GET);
        req.setUrl(urls);
        req.setOkHttpClient(okHttpClient);

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String resultStr = new String(bytes);

            JSONObject jsonObject = JSONObject.parseObject(resultStr);

            if (jsonObject.getString("ERRORCODE")!=null&&jsonObject.getString("ERRORCODE").equals("0")) {
                JSONArray array = jsonObject.getJSONArray("RESULT");
                for (int i = 0; i < array.size(); i++) {
                    String ip = array.getJSONObject(i).getString("ip");
                    int port = Integer.parseInt(array.getJSONObject(i).getString("port"));
                    Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(ip, port));
                    list.add(proxy);
                }
                logger.info("获取代理ip的成功 -> result = {}", resultStr);
            } else {
                logger.error("获取代理ip的失败 -> 错误信息 = {}", resultStr);
            }

        } catch (IOException e) {
            logger.error("获取代理ip失败");
            e.printStackTrace();
        }
        return list;
    }


}
