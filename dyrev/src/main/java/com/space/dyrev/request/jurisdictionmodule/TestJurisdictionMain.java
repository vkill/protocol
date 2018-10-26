package com.space.dyrev.request.jurisdictionmodule;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.request.jurisdictionmodule.service.JurisdictionService;
import com.space.dyrev.request.jurisdictionmodule.service.impl.JurisdictionServiceImpl;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

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
 *        @Date: 2018/10/25 15:40
 *        @Description: 
 **/
public class TestJurisdictionMain {

    private static JurisdictionService js = new JurisdictionServiceImpl();

    private static void testApiPluginConfigV1(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.apiPluginConfigV1(okHttpClient, dyUserEntity);
    }

    // awemeV1ImCloudToken
    private static void testAawemeV1ImCloudToken(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1ImCloudToken(okHttpClient, dyUserEntity);
    }

    // https://security.snssdk.com/passport/token/beat/?
    private static void testPassportTokenBeat(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.passportTokenBeat(okHttpClient, dyUserEntity);
    }

    //awemeV1User
    private static void testAwemeV1User(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1User(okHttpClient, dyUserEntity);
    }

    //awemeV1ImUnreaditems
    private static void testAwemeV1ImUnreaditems(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1ImUnreaditems(okHttpClient, dyUserEntity);
    }

    // awemeV1SpotlightRelation
    private static void testAwemeV1SpotlightRelation(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1SpotlightRelation(okHttpClient, dyUserEntity);
    }

    // awemeV1AbtestParam
    private static void awemeV1AbtestParam(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1AbtestParam(okHttpClient, dyUserEntity);
    }

    // awemeV2platformShareSettings
    private static void awemeV2platformShareSettings(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV2platformShareSettings(okHttpClient, dyUserEntity);
    }

    // awemeV1CheckIn
    private static void awemeV1CheckIn(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1CheckIn(okHttpClient, dyUserEntity);
    }
    // awemeV1License
    private static void awemeV1License(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        js.awemeV1License(okHttpClient, dyUserEntity);
    }


    public static void main(String[] args) {
        DyUserEntity user = SaveAcc.getUser();
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);
 //        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient("121.205.136.174", 32859);
        awemeV1License(okhttpClient, user);

//        String str = "{\"status_code\":0,\"extra\":{\"now\":1540465433000},\"log_pb\":{\"impr_id\":\"20181025190353010008028043479FF9\"},\"shangtang_license\":\"############################################################\\n# SenseTime License\\n# License Product: SenseME\\n# Expiration: 20180101~20180725\\n# License SN: 25b8180f-16a6-4709-b74f-7527fb0ffecc\\n############################################################\\nsGfdd28aT2wZMvt/rIBjvmYKxJ1ufxJMysu3yYMLiRbnSJGHHVJ+N1MQyShM\\nttFH3qUnY6yL/cPX3yOYW29S3yqnGCXk3Hff9yDSRbS6arII8qbNqQFFzQtE\\nLDIvJE5AjhdUilB0mau0h+mj6htGCihrHt8eBWl4SCiDZIB78QbgFFLUAQAA\\nAAEAAABJgqBOMAsJVVFbvnbBhxpRTHEw5zVhOnmN3YqzPNoh6ps4CZPnu0NH\\nCIIRuRPvavVh/HeZZnsRj9FBKzvxxnFSHrlWsj4ZFqxilDmTJSn6jHnaYgG+\\nyazRHPgb2FmugPblmVVoDtjgIZP9i4mZjJoSMHIJl1dficYmpBxlrMboGqIG\\nEiP8mrCf8fqrGHOUBAbMnwjkBjY1C9ruzhDRvyVk6n380UfUdyABzb7GPZtt\\nIlmuvaiAKLKNfaTdpGAtj1TFdoXLdF39F4Kua09YT1iRUjm1qUiJJsKH317A\\nQ5F/eUuCvt3AASTpYPb1GfwgeGv2dhE6kmHzHl/6VQvd6W65AQABAAAAAAAD\\nAAAAAAAAAAAAAABYMlM2TbplQ+Fy6KZ8tsvvljf3xPq5kUk+BDg8DIFPOLUB\\n14uhEBES/OXX76c2wx1jU8BvxGZhQSTbj6JeXeEVsVgfQrpKoqWQPRngT+3t\\nzKnvxCJScSr6UkSkeAjHodTy9TnUX11Ev5Nsk4oBqR7vAGJQHkHCzsfRJyCe\\nYibUu4S1QqPozlQjIf99oEFOFafXencc1is/QW8aQVgKIS8YbGCiIi7LPAeg\\nPkicJyG6lXDhlx3TQZmbFfDyDP5pWICTv/XG2J16rLDPhaXdcS8iNNlt0yUI\\n/m2RtP7Mq0HWDeY6qgctwm2J3CrfP5JROPg+5dIaEHSXqW4s+K68y9dvwsaN\\nFt4UrIwNvUuSu2DNKb2hIn/22LkWvnCDio97n+fDC3LWVK9VuLUfLIin/ID1\\npKaKs8nI3gRmIYJHqx3hfnkwzf+74rp0KHCRj7rYEakG/J27SxouS9qmnmMv\\naXdpC1YUJsA=\\n------------------------------------------------------------\\n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\\n############################################################\\n\"}";
//        JSONObject jsonObject = JSONObject.parseObject(str);
//        String shangtang_license = jsonObject.getString("shangtang_license");
//        System.out.println(shangtang_license);


    }
}
