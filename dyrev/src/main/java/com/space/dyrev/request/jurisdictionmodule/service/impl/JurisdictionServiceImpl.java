package com.space.dyrev.request.jurisdictionmodule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.jurisdictionmodule.params.*;
import com.space.dyrev.request.jurisdictionmodule.service.JurisdictionService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.HttpGet;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
 *                                
 *        @Author: space
 *        @Date: 2018/10/24 22:24
 *        @Description: 获取权限
 **/
@Service("jurisdictionService")
public class JurisdictionServiceImpl implements JurisdictionService {

    private static final Logger logger = LoggerFactory.getLogger(JurisdictionServiceImpl.class);

    @Override
    public void apiPluginConfigV1(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {

        String url = ApiPluginConfigV1Params.constructUrl(dyUserEntity);

        Map header = ApiPluginConfigV1Params.constructHeader(dyUserEntity);

        JSONObject body = ApiPluginConfigV1Params.constructBody(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);
        req.setOkHttpClient(okHttpClient);
        req.setUrl(url);
        req.setHeaders(header);
        req.setBytesBody(body.toJSONString().getBytes());
        req.setBytesType("json");

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String string = GzipGetteer.uncompressToString(bytes);
//            logger.info(string);
            if (string.contains("success")) {
                logger.info("请求权限 ----- is.snssdk.com/api/plugin/config/v1/ -----> 结果 = {}","成功");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String awemeV1ImCloudToken(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        String url = AwemeV1ImCloudTokenParams.constructUrl(dyUserEntity);

        Map header = AwemeV1ImCloudTokenParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String string = GzipGetteer.uncompressToString(bytes);

            JSONObject resultJson = JSONObject.parseObject(string);

            // 结果
            // {"status_code": 0, "status_msg": "", "data": {"token": "98TVfIcyZPOPiYJWskteds9ivBlvWA9S3yK1vWhrpNM0DjZUqTNOMQ"}, "log_pb": {"impr_id": "2018102517034701001803407696252D"}, "extra": {"logid": "2018102517034701001803407696252D", "now": 1540458227992, "fatal_item_ids": []}}
            logger.info("请求权限 ----- is.snssdk.com/api/plugin/config/v1/ -----> 结果 = {}", string);


            return resultJson.getJSONObject("data").getString("token");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";
    }

    @Override
    public String passportTokenBeat(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {

        String url = PassportTokenBeatParams.constructUrl(dyUserEntity);

        Map header = PassportTokenBeatParams.constructHeader(dyUserEntity);

        String body = PassportTokenBeatParams.constructBody(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.POST_OCT);

        req.setUrl(url);

        req.setOkHttpClient(okHttpClient);

        req.setHeaders(header);

        req.setBytesType("json");

        req.setBytesBody(body.getBytes());

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String string = GzipGetteer.uncompressToString(bytes);

            // TODO logger删除
            logger.info("请求权限 ----- security.snssdk.com/passport/token/beat/? -----> 结果 = {}", string);

            if (string.contains("success")) {
                return "0";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Override
    public void awemeV1User(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        String url = AwemeV1UserParams.constructUrl(dyUserEntity);

        Map header = AwemeV1UserParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setUrl(url);

        req.setOkHttpClient(okHttpClient);

        req.setHeaders(header);

        try {
            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            String string = GzipGetteer.uncompressToString(bytes);

            JSONObject jsonResult = JSONObject.parseObject(string);

            if (jsonResult.getInteger("status_code") == 0) {

                // TODO logger删除
                logger.info("请求权限 ----- security.snssdk.com/passport/token/beat/? -----> 结果 = {}", "请求成功，结果太长忽略");
            } else {
                logger.error("请求权限 ----- security.snssdk.com/passport/token/beat/? -----> 结果 = {}", "请求失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String awemeV1ImUnreaditems(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {

        String url = AwemeV1ImUnreaditemsParams.constructUrl(dyUserEntity);

        Map header = AwemeV1ImUnreaditemsParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setUrl(url);

        req.setOkHttpClient(okHttpClient);

        req.setHeaders(header);

        try {

            Response response = OkHttpTool.handleHttpReq(req);

            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            if (jsonResult.getInteger("status_code") == 0) {
                String string = jsonResult.getJSONObject("log_pb").getString("impr_id");
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/im/unreaditems/ -----> impr_id = {}", string);
                return string;
            } else {
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/im/unreaditems/ -----> 结果 = {}", "失败");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


        return "1";
    }

    @Override
    public String awemeV1SpotlightRelation(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        try {
            String url = AwemeV1SpotlightRelationParams.constructUrl(dyUserEntity);
            Map header = AwemeV1SpotlightRelationParams.constructHeader(dyUserEntity);
            Response response = commonGet(url, header, okHttpClient, dyUserEntity);

            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

//            logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/spotlight/relation/? -----> json = {}", jsonResult);

            if (jsonResult.getInteger("status_code") == 0) {
                String string = jsonResult.getJSONObject("log_pb").getString("impr_id");
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/spotlight/relation/? -----> impr_id = {}", string);
                return string;
            } else {
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/spotlight/relation/? -----> 结果 = {}", "失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";
    }

    @Override
    public String awemeV1AbtestParam(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        String url = AwemeV1AbtestParamParams.constructUrl(dyUserEntity);
        Map header = AwemeV1AbtestParamParams.constructHeader(dyUserEntity);
        try {
            Response response = commonGet(url, header, okHttpClient, dyUserEntity);
            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

//            logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/abtest/param -----> json = {}", jsonResult);

            if (jsonResult.getInteger("status_code") == 0) {
                String string = jsonResult.getJSONObject("log_pb").getString("impr_id");
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/abtest/param -----> impr_id = {}", string);
                return string;
            } else {
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/abtest/param -----> 结果 = {}", "失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "1";
    }

    @Override
    public String awemeV2platformShareSettings(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        String url = AwemeV1SpotlightRelationParams.constructUrl(dyUserEntity);
        Map header = AwemeV1SpotlightRelationParams.constructHeader(dyUserEntity);
        try {
            Response response = commonGet(url, header, okHttpClient, dyUserEntity);
            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

//            logger.info("请求权限 ----- aweme.snssdk.com/aweme/v2/platform/share/settings -----> json = {}", jsonResult);

            if (jsonResult.getInteger("status_code") == 0) {
                String string = jsonResult.getJSONObject("log_pb").getString("impr_id");
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v2/platform/share/settings -----> impr_id = {}", string);
                return string;
            } else {
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v2/platform/share/settings -----> 结果 = {}", "失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Override
    public String awemeV1CheckIn(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        // aweme.snssdk.com/aweme/v1/check/in/
        String url = AwemeV1CheckInParams.constructUrl(dyUserEntity);
        Map header = AwemeV1CheckInParams.constructHeader(dyUserEntity);
        try {
            Response response = commonGet(url, header, okHttpClient, dyUserEntity);
            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

//            logger.info("请求权限 -----aweme.snssdk.com/aweme/v1/check/in/ -----> json = {}", jsonResult);

            if (jsonResult.getInteger("status_code") == 0) {
                String string = jsonResult.getJSONObject("log_pb").getString("impr_id");
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/check/in/ -----> impr_id = {}", string);
                return string;
            } else {
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/check/in/ -----> 结果 = {}", "失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Override
    public String awemeV1License(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        // https://aweme.snssdk.com/aweme/v1/license/?
        // AwemeV1LicenseParams

        String url = AwemeV1LicenseParams.constructUrl(dyUserEntity);
        Map header = AwemeV1LicenseParams.constructHeader(dyUserEntity);
        try {
            Response response = commonGet(url, header, okHttpClient, dyUserEntity);
            byte[] bytes = response.body().bytes();

            JSONObject jsonResult = JSONObject.parseObject(GzipGetteer.uncompressToString(bytes));

            logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/license -----> json = {}", jsonResult);

            if (jsonResult.getInteger("status_code") == 0) {
                String string = jsonResult.getJSONObject("log_pb").getString("impr_id");
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/license -----> impr_id = {}", string);
                return string;
            } else {
                logger.info("请求权限 ----- aweme.snssdk.com/aweme/v1/license -----> 结果 = {}", "失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Override
    public String awemeV1AwemeStats(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {
        // https://aweme.snssdk.com/aweme/v1/aweme/stats/


        return null;
    }


    private Response commonGet(String url, Map header, OkHttpClient okHttpClient, DyUserEntity dyUserEntity) throws IOException {
//        String url = AwemeV1ImUnreaditemsParams.constructUrl(dyUserEntity);
//
//        Map header = AwemeV1ImUnreaditemsParams.constructHeader(dyUserEntity);
//
//        RequestEntity req = new RequestEntity(RequestEnum.GET);
//
//        req.setUrl(url);
//
//        req.setOkHttpClient(okHttpClient);
//
//        req.setHeaders(header);
//
//
//        Response response = OkHttpTool.handleHttpReq(req);
//
//        return response;

        return HttpGet.commonGet(url, header, okHttpClient);


    }
}
