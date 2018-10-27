package com.space.dyrev.request.deviceregistermodule.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.dao.DeviceRepository;
import com.space.dyrev.encrypt.CesEncrypt;
import com.space.dyrev.encrypt.TTEncrypt;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.deviceregistermodule.params.*;
import com.space.dyrev.request.deviceregistermodule.service.DeviceRegisterService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.formatutil.ScaleTrans;
import com.space.dyrev.util.httputil.HttpGet;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/15 23:13
 *                                                                                             @Description: 设备注册操作实现类
 **/
@Service("deviceRegisterService")
public class DevRegisterServiceImpl implements DeviceRegisterService {

    private static Logger logger = LoggerFactory.getLogger(DevRegisterServiceImpl.class);

    @Resource
    DeviceRepository deviceRepository;

    @Override
    public DeviceEntity deviceRegister(OkHttpClient okHttpClient, DeviceEntity device) {

        DeviceEntity deviceEntity = device;
        // TO-DO构造okhttp请求

        // 首先构造请求url
        String url = DeviceRegisterParams.constructUrl(deviceEntity);

        // 构造header
        // sdk-version: 1
        // 包括X-SS-QUERIES : urlCode
        // X-SS-REQ-TICKET : 时间戳
        // Accept-Encoding: gzip
        Map header = new HashMap();

        String xssQueries = DeviceRegisterParams.constructXSSQUERIES(deviceEntity);
        byte[] xssBytes = TTEncrypt.getTTEnttyResult(xssQueries.getBytes()); // 加密后数据
        String encode = "";
        try {
            encode = URLEncoder.encode(ScaleTrans.bytesToBase64String(xssBytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("转换失败");
            e.printStackTrace();
        }
        header.put("Accept-Encoding","gzip");
        header.put("sdk-version","1");
        header.put("X-SS-QUERIES", encode);
        header.put("X-SS-REQ-TICKET", CommonParams.getRticket());
        header.put("Connection", "Keep-Alive");
        header.put("HOST", DeviceRegisterParams.DEV_RGS_HOST);


        // 参数，json
        JSONObject jsonObject = DeviceRegisterParams.constructDeviceRegisterJson(deviceEntity);
        String json = jsonObject.toString();
        // 经过gzip压缩的json
        byte[] compressJson = GzipGetteer.compress(json);

        // 经过ttEncrypt的加密
        byte[] ttEnttyResult = TTEncrypt.getTTEnttyResult(compressJson);

        // TO-DO : 发送请求，并获取返回，返回一个DeviceEntity实体类
        try {
            Response response = OkHttpTool.post(okHttpClient, url, header, ttEnttyResult, "octet-stream");

            byte[] resultGzip = response.body().bytes();

            Headers headers = response.headers();

            String headersStr = headers.toString();
            String[] split = headersStr.split("\n");
            JSONObject cookies = new JSONObject();
            String xttlogid = "";
            for (String s : split) {
                if (s.contains("Set-Cookie")) {
                    String[] tmp = s.split(":")[1].split(";")[0].split("=");
                    cookies.put(tmp[0].trim(), tmp[1].trim());
                }
                if (s.contains("X-TT-LOGID")) {
                    xttlogid = s.split(":")[1].trim();
                }
            }
            deviceEntity.setXttlogid(xttlogid);
            deviceEntity.setDeviceCookies(cookies.toJSONString());

            // 格式如下
            // {"server_time":1539945681,"new_user":1,"install_id":46745692722,"device_id":58291941557,"ssid":""}
            JSONObject resultJson = JSONObject.parseObject(GzipGetteer.uncompressToString(resultGzip));


            // TODO logger remove
            logger.info("----- 设备注册成功 ----- 结果 -> device = {}",resultJson.toJSONString());

            deviceEntity.setInstallId(String.valueOf((long)resultJson.get("install_id")));
            deviceEntity.setDeviceId(String.valueOf((long)resultJson.get("device_id")));
            device.setTimeFirstSendInstallApp(String.valueOf(System.currentTimeMillis()));



        } catch (IOException e) {
            logger.error("设备注册失败");
//            e.printStackTrace();
        }

        return deviceEntity;
    }

    @Override
    public boolean xlogV2(DeviceEntity deviceEntity, XlogEnum xlogEnum, OkHttpClient okHttpClient) {
        Map header = XlogV2Params.constructHeader(deviceEntity);
        String url = XlogV2Params.constructV2Url(deviceEntity, xlogEnum);
        JSONObject body = XlogV2Params.constructV2Json(deviceEntity, xlogEnum);


        try {

            byte[] encodeBody = CesEncrypt.cesEncrypt(CesEncrypt.CesEnum.ENCODE, body.toString().getBytes());

            Response response = OkHttpTool.post(okHttpClient, url, header, encodeBody, "octet-stream");

            byte[] uncompress = GzipGetteer.uncompress(response.body().bytes());

            byte[] temp = CesEncrypt.cesEncrypt(CesEncrypt.CesEnum.DECODE, uncompress);

            String result = new String(temp);

            JSONObject jsonObject = JSONObject.parseObject(result);

            logger.info("----- 发送xlogV2 ----- 结果 -> json = {}", result);

            if (jsonObject.get("result").equals("success")) {
                return true;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean uploadDeviceInfo(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {


        return false;
    }

    @Override
    public boolean service1ZAppStats(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        // ib.snssdk.com/service/1/z_app_stats

        String url = Service1ZAppStatsParams.constructUrl(deviceEntity);

        Map header = Service1ZAppStatsParams.constructHeader(deviceEntity);

        JSONObject body = Service1ZAppStatsParams.constructBody(deviceEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);
        req.setUrl(url);
        req.setHeaders(header);
        req.setBytesBody(body.toJSONString().getBytes());
        req.setOkHttpClient(okHttpClient);
        req.setBytesType("json");

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            logger.info("请求权限 ----- ib.snssdk.com/service/1/z_app_stats -----> json = {}", jsonResult);

            if (jsonResult.getString("message").equals("success")) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean apiAdSplashAwemeV14(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        // lf.snssdk.com/api/ad/splash/aweme/v14/?
        String s = ApiAdSplashAwemeV14Params.constructUrl(deviceEntity);
        Map map = ApiAdSplashAwemeV14Params.constructHeader(deviceEntity);

        logger.info("请求权限 ----- lf.snssdk.com/api/ad/splash/aweme/v14 -----> url = {}", s);
        logger.info("请求权限 ----- lf.snssdk.com/api/ad/splash/aweme/v14 -----> header = {}", map);

        try {
            Response response = HttpGet.commonGet(s, map, okHttpClient);
            byte[] bytes = response.body().bytes();



            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            logger.info("请求权限 ----- lf.snssdk.com/api/ad/splash/aweme/v14 -----> json = {}", jsonResult);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean service2AppAlert(DeviceEntity deviceEntity, OkHttpClient okHttpClient) {
        // lf.snssdk.com/service/2/app_alert/?
        String url = Service2AppAlert.constructUrl(deviceEntity);
        Map map = Service2AppAlert.constructHeader(deviceEntity);

        try {
            Response response = HttpGet.commonGet(url, map, okHttpClient);

            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            // TODO 无必要的logger 后期可以删除
            logger.info("请求权限 ----- lf.snssdk.com/service/2/app_alert/? -----> json = {}", jsonResult);

            if (jsonResult.getString("message")!=null&&jsonResult.getString("message").equals("success")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void testSaveDevice(DeviceEntity deviceEntity) {
        DeviceEntity save = deviceRepository.save(deviceEntity);
    }
}
