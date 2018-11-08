package com.space.dyrev.request.applogmodule.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.request.applogmodule.params.AppLogCommonParams;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.request.operationmodule.service.impl.OperationServiceImpl;
import com.space.dyrev.util.dateutils.DateUtil;
import com.space.dyrev.util.deviceinfoutil.CreateDevInfoUtil;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

import java.util.Random;

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
 *                                                                                             @ClassName AppLogManager
 *                                                                                             @Author: space
 *                                                                                             @Description 管理Applog
 *                                                                                             @Date: 2018/10/11 23:29
 **/
public class AppLogManager {

    private JSONObject applog;

    private int eventId = 0;

    // 整串动作都生成的sessionId
    private String sessionId;

    // 上一个视频
    private String beforeGroupId;

    // 下一个视频
    private String nextGroupId;

    private DyUserEntity user;



    /**
     * 构造函数
     * @param dyUserEntity
     */
    public AppLogManager(DyUserEntity dyUserEntity) {
        if (applog == null) {
            this.user = dyUserEntity;
            this.eventId = dyUserEntity.getEventId();// 承接上一次eventId
            this.sessionId = CreateDevInfoUtil.createClientUdid();
            this.applog = initAppLog(dyUserEntity);
        }
    }



    /**
     * 返回applpg的JSON
     * @return
     */
    public JSONObject getJson() {
        return this.applog;
    }


    /**
     * 获取此的eventId
     * @return
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * 构造基本的applog，首先先假设其不是第一次打开抖音
     * @param dyUserEntity
     */
    private JSONObject initAppLog(DyUserEntity dyUserEntity) {
        JSONObject json = new JSONObject();
        JSONArray launch = AppLogCommonParams.launch(sessionId, "1");
        JSONObject timeSync = AppLogCommonParams.timeSync();
        JSONObject header = AppLogCommonParams.constructHeader(dyUserEntity.getDevice());
        JSONArray event = new JSONArray();
        JSONArray eventV3 = new JSONArray();

        json.put("event", event);
        json.put("event_v3", eventV3);
        json.put("launch", launch);
        json.put("magic_tag", "ss_app_log");
        json.put("time_sync", timeSync);
        json.put("header", header);
        json.put("_gen_time", System.currentTimeMillis());
        return json;
    }

    /**
     * 这里仅仅用于实现feed拿到的视频
     * @param imprId
     * @param awemeId
     */
    public void addLike(String imprId, String awemeId, String authorId) {

        if (beforeGroupId == null) {
            this.beforeGroupId = awemeId;
        } else {
            this.beforeGroupId = this.nextGroupId;
            this.nextGroupId = awemeId;
            applog.getJSONArray("event").add(slideUp(authorId, imprId));
        }


        JSONObject event1 = videoPlayEvent(imprId, awemeId, authorId);
        JSONObject event2 = firstFrameLoadtimeEvent();
        JSONObject event = firstFrameLoadTime(awemeId);
        JSONObject event3 = likeEvent(imprId,awemeId,authorId);
        JSONObject event4 = videoPlayFinish(imprId, awemeId, authorId);


        applog.getJSONArray("event").add(eventId);

        applog.getJSONArray("event_v3").add(event1);
        applog.getJSONArray("event_v3").add(event2);
        applog.getJSONArray("event_v3").add(event3);
        applog.getJSONArray("event_v3").add(event4);

    }

    private JSONObject firstFrameLoadTime(String awemeId) {

        JSONObject json = new JSONObject();
        json.put("is_cache", "1");
        json.put("status", "1");
        json.put("duration", "650");
        json.put("is_first", "false");
        json.put("nt", 1);
        json.put("category", "umeng");
        json.put("tag","first_frame_loadtime");
        json.put("label", "perf_monitor");
        json.put("value", awemeId);
        json.put("user_id", this.user.getUserId());
        json.put("session_id", this.sessionId);
        json.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis()));
        json.put("event_id", this.eventId);
        eventId++;
        return json;
    }


    /**
     * 从一个视频滑倒另外视频的动作
     * @return
     */
    private JSONObject slideUp(String authorId, String imprID) {
        JSONObject json = new JSONObject();
        json.put("author_id", authorId);
        json.put("to_group_id", this.nextGroupId);
        json.put("request_id", imprID);
        json.put("from_group_id", this.beforeGroupId);
        json.put("nt", 1);
        json.put("category", "umeng");
        json.put("tag","slide_up");
        json.put("label", "homepage_hot");
        json.put("user_id", this.user.getUserId());
        json.put("session_id", this.sessionId);
        json.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis()));
        json.put("event_id", this.eventId);
        eventId++;



        return json;
    }

    /**
     * videoPlayEvent,点赞的第一步
     * @return
     */
    private JSONObject videoPlayEvent(String imprId, String videoId, String authorId) {
        JSONObject json = new JSONObject();
        json.put("nt", 1);
        json.put("user_id", Long.parseLong(user.getUserId()));
        json.put("event", "video_play");

        JSONObject params = new JSONObject();
        params.put("is_h265", "0");
        params.put("order","5");
        params.put("is_photo", "0");
        params.put("request_id", imprId);
        params.put("group_id", videoId);
        params.put("enter_fullscreen", "0");
        params.put("player_type", "Ijk");
        params.put("tab_name", "output");
        params.put("detail", "1");
        params.put("feed_count", "0");
        params.put("author_id", authorId);
        params.put("previous_page", "homepage_hot");
        params.put("enter_from", "others_homepage");
        json.put("params", params);
        json.put("event_id", eventId);
        eventId++;
        json.put("session_id", sessionId);
        json.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis() - 8000));

        return json;
    }

    /**
     * 点赞第二部
     * @return
     */
    private JSONObject firstFrameLoadtimeEvent() {
        JSONObject json = new JSONObject();
        json.put("nt", 1);
        json.put("user_id", Long.parseLong(user.getUserId()));
        json.put("event", "first_frame_loadtime");

        JSONObject params = new JSONObject();
        int dur = new Random().nextInt(1000);
        params.put("duration", Integer.toString(dur));
        params.put("status", "1");
        params.put("is_cache", "1");
        params.put("is_first", "false");
        params.put("player_type", "Ijk");

        json.put("params", params);
        json.put("event_id", eventId);
        eventId++;
        json.put("session_id", sessionId);
        json.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis() - 6000));
        return json;

    }

    /**
     * 点赞的第三步
     * @param imprId
     * @param videoId
     * @param authorId
     * @return
     */
    private JSONObject likeEvent(String imprId, String videoId, String authorId) {
        JSONObject json = new JSONObject();
        json.put("nt", 1);
        json.put("user_id", Long.parseLong(user.getUserId()));
        json.put("event", "like");

        JSONObject params = new JSONObject();
        //"author_id": "94701427570",
        //"request_id": "201811070041400100160462204528FD",
        //"group_id": "6618109666704493831",
        //"enter_from": "others_homepage"
        params.put("author_id", authorId);
        params.put("request_id", imprId);
        params.put("group_id", videoId);
        params.put("enter_from", "others_homepage");

        json.put("event_id", eventId);
        json.put("params", params);
        eventId++;
        json.put("session_id", sessionId);
        json.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis() - 6000));
        return json;
    }


    private JSONObject videoPlayFinish(String imprId, String videoId, String authorId) {
        JSONObject json = new JSONObject();
        json.put("nt", 1);
        json.put("user_id", Long.parseLong(user.getUserId()));
        json.put("event", "video_play_finish");

        JSONObject params = new JSONObject();

        params.put("author_id", authorId);
        params.put("tab_name", "output");
        params.put("request_id", imprId);
        params.put("group_id", videoId);
        params.put("enter_from", "others_homepage");

        json.put("params", params);
        json.put("event_id", eventId);
        eventId++;
        json.put("session_id", sessionId);
        json.put("datetime", DateUtil.getFormatFromTs(System.currentTimeMillis() - 4000));
        return json;
    }




    public static void main(String[] args) {
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);
        DyUserEntity user = SaveAcc.getUser();

        OperationService os = new OperationServiceImpl();
        AppLogManager applog = new AppLogManager(user);
        JSONObject json = os.feed(okhttpClient, user, user.getDevice(), "json");
        String imprId = json.getString("impr_id");
        JSONArray awemeList = json.getJSONArray("aweme_list");
        for (int i = 0; i < awemeList.size(); i++) {
            JSONObject tmp = (JSONObject)awemeList.get(i);
            String awemeId = tmp.getString("aweme_id");
            String authorId = tmp.getString("author_id");
            applog.addLike(imprId, awemeId, authorId);
        }
        System.out.println(applog.getJson());
    }

}
