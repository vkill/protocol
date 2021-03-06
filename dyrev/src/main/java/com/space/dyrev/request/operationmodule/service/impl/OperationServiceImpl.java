package com.space.dyrev.request.operationmodule.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.request.operationmodule.params.*;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.httputil.HttpGet;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
 *
 *        @Author: space
 *        @Date: 2018/10/24 19:27
 *        @Description: 操作类，点赞关注等实现方法
 **/
@Service("operationService")
public class OperationServiceImpl implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Override
    public String digg(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String awemeId) {

        String url = DiggParams.constructUrl(dyUserEntity, awemeId);

        Map header = DiggParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);
        req.setUrl(url);
        req.setHeaders(header);

        Response response = null;
        try {
            response = OkHttpTool.handleHttpReq(req);
            byte[] bytes = response.body().bytes();

            String result = new String(bytes);
//                    GzipGetteer.uncompressToString(bytes);

            JSONObject msg = JSONObject.parseObject(result);

            if (msg.getString("status_code")!=null && msg.getString("status_code").equals("0")) {
                if (msg.getString("is_digg")!=null&&msg.getString("is_digg").equals("0")) {
                    return msg.getJSONObject("log_pb").getString("impr_id");
                }
            }
            logger.info("点赞结果 -> msg={}", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "1";

    }

    @Override
    public String follow(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String userId) {
        String url = FollowParams.constructUrl(dyUserEntity, userId);

        Map header = FollowParams.constructHeader(dyUserEntity);

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);
        req.setUrl(url);
        req.setHeaders(header);

        Response response = null;
        try {
            response = OkHttpTool.handleHttpReq(req);
            byte[] bodyBytes = response.body().bytes();
            String result = new String(bodyBytes);

            JSONObject msg = JSONObject.parseObject(result);

            logger.info("关注结果 -> msg={}", msg);


            if (msg.getString("status_code")!=null && msg.getString("status_code").equals("0")) {
                if (msg.getString("status_msg")!=null && msg.getString("status_msg").equals("关注成功")) {
//                    logger.info("关注结果impr_id -> msg={}", msg.getJSONObject("log_pb").getString("impr_id"));
                    return msg.getJSONObject("log_pb").getString("impr_id");
                } else  {
                    logger.error("没有找到相关的impr_id");
                }
            } else {
                logger.error("关注返回数据查找status_code失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Override
    public String modify(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) {

        String url = ModifyParams.constructUrl(dyUserEntity);

        Map header = ModifyParams.constructHeader(dyUserEntity);

        Map body = ModifyParams.constructBody(dyUserEntity);

        RequestEntity requestEntity = new RequestEntity(RequestEnum.POST_FORM);

        requestEntity.setUrl(url);
        requestEntity.setHeaders(header);
        requestEntity.setBody(body);
        requestEntity.setOkHttpClient(okHttpClient);

        try {
            Response response = OkHttpTool.handleHttpReq(requestEntity);
            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void searchUser(OkHttpClient okHttpClient, DyUserEntity dyUserEntity,String userId) {
        // aweme.snssdk.com/aweme/v1/user/?

        String url = SearchUserParams.constructUrl(dyUserEntity, userId);

        Map map = SearchUserParams.constructHeader(dyUserEntity);


        try {
            Response response = HttpGet.commonGet(url, map, okHttpClient);

            byte[] bodyBytes = response.body().bytes();

            String result = GzipGetteer.uncompressToString(bodyBytes);

            response.body().close();

//            logger.info(" ----- aweme.snssdk.com/aweme/v1/user/? 搜索用户结果 ----- -> user = {}", result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public DyUserEntity passportMobileLogin(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String captcha) throws Exception{
        // is.snssdk.com/passport/mobile/login/

        DyUserEntity resultUser = dyUserEntity;

        long time = System.currentTimeMillis();

        String url = PassportMobileLoginParams.constructUrl(dyUserEntity, time);

        Map header = PassportMobileLoginParams.constructHeader(dyUserEntity, time);

        Map body = PassportMobileLoginParams.constructBody(dyUserEntity, captcha, time);

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        req.setBody(body);

        Response response = OkHttpTool.handleHttpReq(req);

        String headers = response.headers().toString();

        logger.info(" ----- is.snssdk.com/passport/mobile/login/ headers情况 ----- -> headers = {}", headers);

        String result = GzipGetteer.uncompressToString(response.body().bytes());

        logger.info(" ----- is.snssdk.com/passport/mobile/login/ 登陆情况 ----- -> result = {}", result);



        return resultUser;

    }

    @Override
    public DyUserEntity login(OkHttpClient okHttpClient, DyUserEntity dyUserEntity) throws Exception{

        DyUserEntity user = dyUserEntity;

        long time = System.currentTimeMillis();

        String url = LoginParams.constructUrl(dyUserEntity);

        Map header = LoginParams.constructHeader(dyUserEntity);

        Map body = LoginParams.constructBody(dyUserEntity, dyUserEntity.getCaptcha());

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        req.setBody(body);


        Call call = okHttpClient.newCall(constructPost(req));
        Response response = call.execute();

        Headers responseHeaders = response.headers();

        JSONObject msg = JSONObject.parseObject(GzipGetteer.uncompressToString(response.body().bytes()));
//        System.out.println(msg);
        if (msg.get("message").equals("error")){
            JSONObject msg1 = (JSONObject) msg.get("data");

//            System.out.println(String.valueOf(msg1.get("captcha")));
            user.setCaptcha(String.valueOf(msg1.get("captcha")));

            user.setCaptcha(true);

        }else {
            int responseHeadersLength = responseHeaders.size();
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < responseHeadersLength; i++){
                String headerName = responseHeaders.name(i);
                String headerValue = responseHeaders.value(i);
//            System.out.print("TAG----------->Name:"+headerName+"------------>Value:"+headerValue+"\n");
                if(headerName.equals("Set-Cookie")){
                    String []temp = headerValue.split(";")[0].split("=");
                    jsonObject.put(temp[0], temp[1]);
                }

//                logger.info("----- header = {} ----->  value = {}", headerName ,headerValue);
                if (headerName.equals("X-Tt-Token")) {
//                    String tmp = s.split(":")[1];
//                    user.setxTtToken(tmp);
                    user.setxTtToken(headerValue);
//                    logger.info("----- X-Tt-Token ----- = {}", headerValue);
                }

                if (headerName.equals("X-Tt-Token-Sign")) {
//                    user.setxTtTokenSign(s.split(":")[1]);
                    user.setxTtTokenSign(headerValue);
//                    logger.info("----- X-Tt-Token-Sign ----- = {}", headerValue);
                }
            }
            user.setCaptcha(false);
            user.setUserCookies(jsonObject.toJSONString());
            logger.info("----- 电话号码 = {}，登陆成功 -----", (user.getArea()+" "+user.getAccount()));
        }

        return user;


    }

    @Override
    public String getUidByVideoId(OkHttpClient okHttpClient, String aweme_id) throws Exception{

        String url = GetUidByVideoIdParams.constructUrl(aweme_id);

        Map header = GetUidByVideoIdParams.constructHeader();

        RequestEntity req = new RequestEntity(RequestEnum.GET);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        Response response = OkHttpTool.handleHttpReq(req);

        //下面对返回的response进行处理以获取用户id
        String result = "";
        String resultLine = GzipGetteer.uncompressToString(response.body().bytes());
        String []temp1 = resultLine.split(", ");
        for(int i = 0;i < temp1.length;i++){
            if(temp1[i].split(":")[0].equals("\"author_user_id\"")){
                result = temp1[i].split(":")[1];
            }
        }
        return result;
    }

    @Override
    public boolean digg270(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String aweme_id) {

        String url = Digg270Params.constructUrl(dyUserEntity, aweme_id);

        Map header = Digg270Params.constructHeader(dyUserEntity);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Map<String,String> headerParams = header;
        for(String key : headerParams.keySet()){
            builder.addHeader(key, headerParams.get(key));
        }
        Request request = builder.get().build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String string = GzipGetteer.uncompressToString(response.body().bytes());
            JSONObject jsonObject = JSONObject.parseObject(string);

            // TODO - 点赞的输出结果被我注释掉了
//            logger.info(" ----- 帐号 = {} 点赞视频 = {} -----> 点赞结果 = {}", (dyUserEntity.getArea() + " " + dyUserEntity.getAccount()), aweme_id, string);

            if (jsonObject.getInteger("status_code") == 0) {
                // 请求成功

                if (jsonObject.getInteger("is_digg") == 1) {
                    // 点赞失败
//                    logger.info(" ----- 帐号 = {}，点赞视频 = {} -----> 点赞结果 = {}", (dyUserEntity.getArea() + " " + dyUserEntity.getAccount()), aweme_id,"点赞失败");
                    return false;
                } else {
                    // 点赞成功
//                    logger.info(" ----- 帐号 = {} -----> 点赞结果 = {}", (dyUserEntity.getArea() + " " + dyUserEntity.getAccount()), "点赞成功");
                    return true;
                }

            }

//            System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<String> feed(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, DeviceEntity deviceEntity) {
//        String _rticket = String.valueOf(System.currentTimeMillis());
//        char []temp = _rticket.toCharArray();
//        String ts = "";
//        for(int i = 0;i < temp.length - 3;i++){
//            ts += temp[i];
//        }
//        long temp_ts = Long.parseLong(ts);
//        temp_ts ++;
//        ts = String.valueOf(temp_ts);
//
//        String url = "https://aweme.snssdk.com/aweme/v1/feed/?type=0&max_cursor=0&min_cursor=" +
//                "0&count=6&volume=0.0&pull_type=0&need_relieve_aweme=0&filter_warn=0&req_from=" +
//                "enter_auto&is_cold_start=1&ts="+ts+"&app_type=normal&" +
//                "os_api="+CommonParams.OS_API +"&device_type="+deviceEntity.getDeviceType()+"&device_platform=" +
//                "android&ssmix=a&manifest_version_code=270&dpi="+deviceEntity.getDpi()+"&uuid="+deviceEntity.getUuid()+"&versi" +
//                "on_code=270&app_name=aweme&version_name=2.7.0&openudid="+deviceEntity.getOpenudid()+"&device_i" +
//                "d="+deviceEntity.getDeviceId()+"&resolution="+deviceEntity.getResolution()+"&os_versi" +
//                "on="+CommonParams.OS_VERSION+"&language=zh&device_brand="+deviceEntity.getDeviceBrand()+"&ac=wifi&update_vers" +
//                "ion_code=2702&aid=1128&ch" +
//                "annel=meizu&_rticket="+_rticket+"&as=a1iosdfgh&cp=androide1";
//
//
//        Map<String, String> header = new HashMap<String, String>();
//        header.put("Accept-Encoding","gzip");
//        header.put("Host","aweme.snssdk.com");
//        header.put("Connection","Keep-Alive");
//        //header.put("Cookie","install_id="+iid+";qh[360]=1;odin_tt="+odin_tt+";sid_guard="+sid_guard+";uid_tt="+uid_tt+";sid_tt="+sid_tt+";sessionid="+sessionid);
//        header.put("User-Agent","okhttp/3.10.0.1");
//        header.put("X-SS-REQ-TICKET",_rticket);
//        header.put("sdk-version","1");
//
//        RequestEntity requestEntity = new RequestEntity(RequestEnum.GET);
//        requestEntity.setOkHttpClient(okHttpClient);
//        requestEntity.setUrl(url);
//        requestEntity.setHeaders(header);
//        String result = "";
//        try {
//            Response response = OkHttpTool.handleHttpReq(requestEntity);
//            result = GzipGetteer.uncompressToString(response.body().bytes());
//
//            // 2018-11-07 修改输出格式，返回一个json
//            // 返回格式 {"impr_id":"xxxxxxxxxxxxxxxx", "data":[{"authorId":"999999999","videoId":"999999999"},{{"authorId":"999999999","videoId":"999999999"}}]}
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String result = feedString(okHttpClient, dyUserEntity, deviceEntity);

        return getAwemeIdList(result);
    }



    @Override
    public JSONObject feed(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, DeviceEntity deviceEntity, String type) {
        if (type.equals("json")) {

            String resultStr = feedString(okHttpClient, dyUserEntity, deviceEntity);
            JSONObject feed = JSONObject.parseObject(resultStr);
            JSONObject result = new JSONObject();
            if (feed.getInteger("status_code") == 0) {
                // 拉取成功
                result.put("impr_id", feed.getJSONObject("log_pb").getString("impr_id"));
                JSONArray data = new JSONArray();
                JSONArray list = feed.getJSONArray("aweme_list");
                result.put("aweme_list", data);
                for (int i = 0; i < list.size(); i++) {
                    JSONObject aweme = (JSONObject) list.get(i);
//                    logger.info("拉取某个视频信息 = {}", aweme);
                    String uid = aweme.getJSONObject("author").getString("uid");
                    String awemeId = aweme.getJSONObject("status").getString("aweme_id");
//                    logger.info("拉取某个视频信息 作者id = {}，视频id = {}", uid, awemeId);
                    JSONObject tmp = new JSONObject();
                    tmp.put("author_id", uid);
                    tmp.put("aweme_id", awemeId);
                    data.add(tmp);
                }
                return result;
            }
//            logger.info(" feed 拉取视频结果 = {}", feed);
        }

        //{"impr_id":"xxxxxxxxxxxxxxxx", "aweme_list":[{"authorId":"999999999","aweme_id":"999999999"},{{"authorId":"999999999","videoId":"999999999"}}]}
        return null;
    }

    @Override
    public void BrowseVideo(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String awemeId) {

        String url = BrowseVideo.constructUrl(dyUserEntity);

        Map header = BrowseVideo.constructHeader(dyUserEntity);

        Map body = BrowseVideo.constructBody(dyUserEntity, awemeId);

        RequestEntity req = new RequestEntity(RequestEnum.POST_FORM);

        req.setOkHttpClient(okHttpClient);

        req.setUrl(url);

        req.setHeaders(header);

        req.setBody(body);

        String result = "";
        Call call = okHttpClient.newCall(constructPost(req));
        try {
            Response response = call.execute();
            result = GzipGetteer.uncompressToString(response.body().bytes());
            //返回结果示例：{"status_code":0,"extra":{"now":1541659384000},"log_pb":{"impr_id":"201811081443040100180260121585E3"}}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 公共的feed
     * @param okHttpClient
     * @param dyUserEntity
     * @param deviceEntity
     * @return
     */
    private String feedString(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, DeviceEntity deviceEntity) {
        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);

        String url = "https://aweme.snssdk.com/aweme/v1/feed/?type=0&max_cursor=0&min_cursor=" +
                "0&count=6&volume=0.0&pull_type=0&need_relieve_aweme=0&filter_warn=0&req_from=" +
                "enter_auto&is_cold_start=1&ts="+ts+"&app_type=normal&" +
                "os_api="+CommonParams.OS_API +"&device_type="+deviceEntity.getDeviceType()+"&device_platform=" +
                "android&ssmix=a&manifest_version_code=270&dpi="+deviceEntity.getDpi()+"&uuid="+deviceEntity.getUuid()+"&versi" +
                "on_code=270&app_name=aweme&version_name=2.7.0&openudid="+deviceEntity.getOpenudid()+"&device_i" +
                "d="+deviceEntity.getDeviceId()+"&resolution="+deviceEntity.getResolution()+"&os_versi" +
                "on="+CommonParams.OS_VERSION+"&language=zh&device_brand="+deviceEntity.getDeviceBrand()+"&ac=wifi&update_vers" +
                "ion_code=2702&aid=1128&ch" +
                "annel=meizu&_rticket="+_rticket+"&as=a1iosdfgh&cp=androide1";


        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
        //header.put("Cookie","install_id="+iid+";qh[360]=1;odin_tt="+odin_tt+";sid_guard="+sid_guard+";uid_tt="+uid_tt+";sid_tt="+sid_tt+";sessionid="+sessionid);
        header.put("User-Agent","okhttp/3.10.0.1");
        header.put("X-SS-REQ-TICKET",_rticket);
        header.put("sdk-version","1");

        RequestEntity requestEntity = new RequestEntity(RequestEnum.GET);
        requestEntity.setOkHttpClient(okHttpClient);
        requestEntity.setUrl(url);
        requestEntity.setHeaders(header);
        String result = "";
        try {
            Response response = OkHttpTool.handleHttpReq(requestEntity);
            result = GzipGetteer.uncompressToString(response.body().bytes());

            return result;
            // 2018-11-07 修改输出格式，返回一个json
            // 返回格式 {"impr_id":"xxxxxxxxxxxxxxxx", "data":[{"authorId":"999999999","videoId":"999999999"},{{"authorId":"999999999","videoId":"999999999"}}]}

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Request constructPost(RequestEntity requestEntity){
        // 构建POST请求，并设置请求消息头
        //requestEntity中包含三部分，Url、Header和Body
        FormBody.Builder formBody = new FormBody.Builder();     //创建表单请求体

        Request.Builder builder = new Request.Builder();
        builder.url(requestEntity.getUrl());
        Map<String, String> headerParams = requestEntity.getHeaders();
        Map<String, String> bodyParams = requestEntity.getBody();
        for(String key : headerParams.keySet()){        //添加header信息
            builder.addHeader(key, headerParams.get(key).trim());
        }
        for(String key : bodyParams.keySet()){      //添加body信息
//            builder.post(formBody.build()).build();
            formBody.add(key, bodyParams.get(key));
        }
        Request request = builder.post(formBody.build()).build();


        return request;
    }

    public static ArrayList<String> getAwemeIdList(String temp){


        ArrayList<String> result = new ArrayList<>();
        String []temp1 = temp.split("\"aweme_id\"");
        for(int i = 1;i < temp1.length;i++){
            String []temp2 = temp1[i].split(",");
            result.add(temp2[0]);
        }

        ArrayList<String> resultToReturn = new ArrayList<>();
        for(int i = 0;i < result.size();i+=3){
            char []array = result.get(i).toCharArray();
            String str_temp ="";
            for(int j = 0;j < array.length;j++){
                if (Character.isDigit(array[j])){
                    str_temp += array[j];
                }
            }
            resultToReturn.add(str_temp);
        }
        return resultToReturn;
    }
}
