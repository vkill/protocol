package com.space.dyrev.request.jurisdictionmodule.service;

import com.space.dyrev.commonentity.DyUserEntity;
import okhttp3.OkHttpClient;

/**
 * 获取权限的接口
 */
public interface JurisdictionService {

    /**
     * https://is.snssdk.com/api/plugin/config/v1/?
     * @param okHttpClient
     * @param dyUserEntity
     */
    void apiPluginConfigV1(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);


    /**
     * 返回有个token，很奇怪的，暂时不知道作用
     * aweme.snssdk.com/aweme/v1/im/cloud/token
     * result json -> {"status_code": 0, "status_msg": "", "data": {"token": "98TVfIcyZPOPiYJWskteds9ivBlvWA9S3yK1vWhrpNM0DjZUqTNOMQ"}, "log_pb": {"impr_id": "2018102517034701001803407696252D"}, "extra": {"logid": "2018102517034701001803407696252D", "now": 1540458227992, "fatal_item_ids": []}}
     * @param okHttpClient
     * @param dyUserEntity
     * @return 成功返回token 失败返回 "1"
     */
    String awemeV1ImCloudToken(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);

    // https://security.snssdk.com/passport/token/beat/?

    /**
     * https://security.snssdk.com/passport/token/beat/?
     * {"message":"success"}
     * @param okHttpClient
     * @param dyUserEntity
     * @return "0"是成功 "1"是失败
     */
    String passportTokenBeat(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);


    /**
     * https://aweme.snssdk.com/aweme/v1/user/?retry_type=no_retry&iid=47106823699&device_id=57616910195&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&openudid=cd5deef67704a09e&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540353336353&ts=1540353336&as=a115ceecd8330b9d6f4355&cp=e13ab55180feccd4e1QuYy&mas=015d0f0709defa1e0647666fcf8e0bcb55acaccc2c666c269c460c HTTP/1.1
     * {"status_code":0,"user":{"reflow_page_gid":0,"country":"","video_icon_virtual_URI":"","avatar_medium":{"uri":"8e4b00124d6157b79d58","url_list":["https://p1.pstatp.com/aweme/720x720/8e4b00124d6157b79d58.jpeg","https://pb3.pstatp.com/aweme/720x720/8e4b00124d6157b79d58.jpeg","https://pb3.pstatp.com/aweme/720x720/8e4b00124d6157b79d58.jpeg"]},"is_ad_fake":false,"region":"CN","has_email":false,"is_discipline_member":false,"dou_plus_share_location":0,"watch_status":false,"birthday":"1995-05-01","weibo_url":"","followers_detail":[{"apple_id":"1142110895","download_url":"https://d.douyin.com/JsvN/","package_name":"com.ss.android.ugc.aweme","app_name":"aweme","name":"抖音","icon":"http://p3.pstatp.com/origin/50ec00079b64de2050dc","fans_count":16,"open_url":"snssdk1128://user/profile/99689411821?"},{"app_name":"news_article","name":"头条","icon":"http://p3.pstatp.com/origin/50ed00079a1b6b8d1fb1","fans_count":0,"open_url":"sslocal://ttactive?ios_url=https%3a%2f%2fitunes.apple.com%2fcn%2fapp%2fid529092160&android_url=http%3A%2F%2Fs0.pstatp.com%2Fsite%2Fdownload%2Fapp%2Fapk%2Fhotsoon%2F2.8.5%2Flivestream_grey2850_v2.8.0_0bbd20e.apk&app_name=%E4%BB%8A%E6%97%A5%E5%A4%B4%E6%9D%A1&android_pkg_name=com.ss.android.article.news&ios_scheme=%5b%22ttnewsinhousesso%22%2c%22ttnewssso%22%5d","apple_id":"529092160","download_url":"https://d.toutiao.com/YjjY/","package_name":"com.ss.android.article.news"},{"package_name":"com.ss.android.ugc.live","app_name":"live_stream","name":"火山","icon":"http://p3.pstatp.com/origin/551900041a7e00ec86ca","fans_count":0,"open_url":"snssdk1112://profile?id=99689411821","apple_id":"1086047750","download_url":"http://d.huoshanzhibo.com/eFvB/"}],"verification_type":0,"live_commerce":false,"short_id":"1018048557","hide_search":false,"school_poi_id":"","star_use_new_download":true,"location":"","hide_location":false,"is_verified":true,"twitter_name":"","live_verify":0,"activity":{"use_music_count":0,"digg_count":0},"aweme_count":0,"video_icon":{"uri":"","url_list":[]},"youtube_channel_title":"","following_count":33,"google_account":"","react_setting":0,"apple_account":0,"recommend_reason_relation":"","can_modify_school_info":true,"authority_status":0,"school_type":3,"youtube_channel_id":"","shield_follow_notice":0,"with_shop_entry":false,"district":"","favoriting_count":72,"province":"","account_region":"","prevent_download":false,"shield_comment_notice":0,"follower_status":0,"iso_country_code":"","with_item_commerce_entry":false,"avatar_thumb":{"uri":"8e4b00124d6157b79d58","url_list":["https://p1.pstatp.com/aweme/100x100/8e4b00124d6157b79d58.jpeg","https://pb3.pstatp.com/aweme/100x100/8e4b00124d6157b79d58.jpeg","https://pb3.pstatp.com/aweme/100x100/8e4b00124d6157b79d58.jpeg"]},"unique_id":"","has_facebook_token":false,"cover_url":[{"uri":"c8510002be9a3a61aad2","url_list":["http://p1.pstatp.com/obj/c8510002be9a3a61aad2","http://pb3.pstatp.com/obj/c8510002be9a3a61aad2","http://pb3.pstatp.com/obj/c8510002be9a3a61aad2"]}],"weibo_schema":"","original_musician":{"music_count":0,"music_used_count":0,"digg_count":0},"mplatform_followers_count":16,"city":"","story_count":0,"tw_expire_time":0,"geofencing":null,"download_setting":0,"college_name":"","special_lock":1,"has_youtube_token":false,"share_info":{"bool_persist":1,"share_url":"www.iesdouyin.com/share/user/99689411821?u_code=1669bkie2","share_weibo_desc":"在抖音，记录美好生活！","share_desc":"在抖音，记录美好生活！","share_title":"快来加入抖音，让你发现最有趣的我！","share_qrcode_url":{"uri":"8aef00244541b58c0465","url_list":["http://p3.pstatp.com/obj/8aef00244541b58c0465","http://pb9.pstatp.com/obj/8aef00244541b58c0465","http://pb3.pstatp.com/obj/8aef00244541b58c0465"]},"share_image_url":{"uri":"8e4b00124d6157b79d58","url_list":["http://p1.pstatp.com/obj/8e4b00124d6157b79d58","http://pb3.pstatp.com/obj/8e4b00124d6157b79d58","http://pb3.pstatp.com/obj/8e4b00124d6157b79d58"]}},"platform_sync_info":[],"secret":0,"realname_verify_status":0,"twitter_id":"","weibo_verify":"","fb_expire_time":0,"commerce_user_level":0,"uid":"99689411821","language":"zh-Hans","reflow_page_uid":0,"signature":"","follow_status":0,"weibo_name":"","with_dou_entry":true,"has_twitter_token":false,"youtube_expire_time":1531281244,"has_activity_medal":false,"unique_id_modify_time":1540353337,"ins_id":"","gender":1,"follower_count":16,"bind_phone":"135*****111","user_canceled":false,"share_qrcode_uri":"8aef00244541b58c0465","constellation":2,"accept_private_policy":false,"show_gender_strategy":0,"is_gov_media_vip":false,"duet_setting":0,"policy_version":null,"verify_info":"","shield_digg_notice":0,"is_phone_binded":true,"enterprise_verify_reason":"","profile_tab_type":0,"with_commerce_newbie_task":false,"total_favorited":0,"room_id":0,"school_name":"","with_commerce_entry":false,"live_agreement":0,"login_platform":0,"has_insights":false,"story_open":true,"dongtai_count":0,"live_agreement_time":0,"download_prompt_ts":0,"is_block":false,"sync_to_toutiao":0,"user_rate":1,"show_image_bubble":false,"register_time":1528083914,"with_douplus_entry":false,"comment_setting":0,"with_new_goods":false,"nickname":"猪猪侠","custom_verify":"","is_binded_weibo":false,"avatar_uri":"8e4b00124d6157b79d58","neiguang_shield":0,"enroll_year":"","need_recommend":0,"has_orders":false,"status":1,"avatar_larger":{"uri":"8e4b00124d6157b79d58","url_list":["https://p1.pstatp.com/aweme/1080x1080/8e4b00124d6157b79d58.jpeg","https://pb3.pstatp.com/aweme/1080x1080/8e4b00124d6157b79d58.jpeg","https://pb3.pstatp.com/aweme/1080x1080/8e4b00124d6157b79d58.jpeg"]},"with_fusion_shop_entry":false,"is_flowcard_member":false},"extra":{"now":1540353337000},"log_pb":{"impr_id":"201810241155370100120341556714A6"}}
     * @param okHttpClient
     * @param dyUserEntity
     */
    void awemeV1User(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);




    /**
     * https://aweme.snssdk.com/aweme/v1/im/unreaditems/?cursor=0&ts=1540353336&app_type=normal&os_api=25&device_platform=android&device_type=Redmi%204X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353336365&as=a1851e9c6883db0daf4355&cp=ec34b05f8afacbdae1Sc%5Bg&mas=01daf8ca67d591d604d1b5c01707c2c0ebacaccc2c6686260c4626 HTTP/1.1
     * {"status_code": 0, "status_msg": "", "log_pb": {"impr_id": "2018102411553701001604620771700C"}, "extra": {"logid": "2018102411553701001604620771700C", "now": 1540353337745, "fatal_item_ids": []}}
     * @param okHttpClient okhttp对象
     * @param dyUserEntity 抖音帐号实体类
     * @return 成功返回impr_id 失败返回 "1"
     */
    String awemeV1ImUnreaditems(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);




    /**
     * 请求关注列表的推送
     * https://aweme.snssdk.com/aweme/v1/spotlight/relation/?count=1000&with_fstatus=1&max_time=1540353336502&min_time=0&ts=1540353337&app_type=normal&os_api=25&device_platform=android&device_type=Redmi%204X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353336551&as=a195ae1c29a3fb6dff4355&cp=ed36b15896fbcad4e1_cMg&mas=018253de99ac92b3bd792bcb5175a1e376acaccc2c6666266c4666 HTTP/1.1
     * {"log_pb": {"impr_id": "20181024115537010015088049790CF2"}, "extra": {"logid": "20181024115537010015088049790CF2", "now": 1540353337874, "fatal_item_ids": []}, "has_more": 1, "status_code": 0, "followings": [{"uid": "99689411821", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/aweme/100x100/8e4b00124d6157b79d58.jpeg", "https://pb3.pstatp.com/aweme/100x100/8e4b00124d6157b79d58.jpeg", "https://pb3.pstatp.com/aweme/100x100/8e4b00124d6157b79d58.jpeg"], "uri": "8e4b00124d6157b79d58"}, "nickname": "\u732a\u732a\u4fa0", "unique_id": "", "follow_status": 2}, {"uid": "61650613388", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u5c0f\u6e05\u65b0", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/910900204f7316b1fbc5.jpeg", "https://pb9.pstatp.com/aweme/100x100/910900204f7316b1fbc5.jpeg", "https://pb3.pstatp.com/aweme/100x100/910900204f7316b1fbc5.jpeg"], "uri": "910900204f7316b1fbc5"}, "nickname": "I\ud83c\udf7cLearn to be \ud83c\udf38S", "unique_id": "I100807875", "follow_status": 1}, {"uid": "87145645137", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/thumb/5ac7001707f5b66e2b3e"], "uri": ""}, "nickname": "\ud83d\udc8b\u5c0f\u5973\u4eba\ud83d\udc8b", "unique_id": "", "follow_status": 1}, {"uid": "59021821479", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "\u4f18\u8d28\u89c6\u9891\u521b\u4f5c\u8005", "signature": "\ud83c\udf39\ud83d\udc30\ud83c\udf39\ud83d\udc30\ud83c\udf39\u8054\u7cfbVx : qihuansenlinceo", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/85f1002135888c931077.jpeg", "https://pb9.pstatp.com/aweme/100x100/85f1002135888c931077.jpeg", "https://pb3.pstatp.com/aweme/100x100/85f1002135888c931077.jpeg"], "uri": "85f1002135888c931077"}, "nickname": "\ud83d\udd25\u5c0f\u5154\u59ec", "unique_id": "", "follow_status": 1}, {"uid": "72934523533", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/9b95001abe4501dfd626.jpeg", "https://pb9.pstatp.com/aweme/100x100/9b95001abe4501dfd626.jpeg", "https://pb3.pstatp.com/aweme/100x100/9b95001abe4501dfd626.jpeg"], "uri": "9b95001abe4501dfd626"}, "nickname": "@\u7cbe\u7075\u59d0\u59d0@", "unique_id": "", "follow_status": 1}, {"uid": "56997873675", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u9ede\u4e86\u95dc\u6ce8\u4f60\u5c31\u662f\u6211\u7684\u4eba\u4e86.", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/db9d001a1d602be287c6.jpeg", "https://pb9.pstatp.com/aweme/100x100/db9d001a1d602be287c6.jpeg", "https://pb3.pstatp.com/aweme/100x100/db9d001a1d602be287c6.jpeg"], "uri": "db9d001a1d602be287c6"}, "nickname": "\ud83c\udf80    Tsui", "unique_id": "Tsui7758258", "follow_status": 1}, {"uid": "100465405088", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u5973\u4fa0 \u58ee\u58eb \u5173\u6ce8\u4e00\u4e0b\u518d\u8d70", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/90ed0008f33dbba33c1e.jpeg", "https://pb9.pstatp.com/aweme/100x100/90ed0008f33dbba33c1e.jpeg", "https://pb3.pstatp.com/aweme/100x100/90ed0008f33dbba33c1e.jpeg"], "uri": "90ed0008f33dbba33c1e"}, "nickname": "\u77ed\u7b11\u8bdd", "unique_id": "", "follow_status": 1}, {"uid": "70394916506", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u672c\u6c6a\u4e24\u5c81\uff0c\u5355\u8eab\u3002", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/aweme/100x100/6f92000ca8ad9beb9bf2.jpeg", "https://pb3.pstatp.com/aweme/100x100/6f92000ca8ad9beb9bf2.jpeg", "https://pb3.pstatp.com/aweme/100x100/6f92000ca8ad9beb9bf2.jpeg"], "uri": "6f92000ca8ad9beb9bf2"}, "nickname": "\u4e8c\u54c8\u754c\u6bd4\u8f83\u5e05\u7684\u515c\u515c", "unique_id": "DouBuLaDou666", "follow_status": 1}, {"uid": "59908916176", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "vx:qcy8080", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/8adb004ae4ea8f8c3a00.jpeg", "https://pb9.pstatp.com/aweme/100x100/8adb004ae4ea8f8c3a00.jpeg", "https://pb3.pstatp.com/aweme/100x100/8adb004ae4ea8f8c3a00.jpeg"], "uri": "8adb004ae4ea8f8c3a00"}, "nickname": "\u5b87", "unique_id": "", "follow_status": 1}, {"uid": "101123290141", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "\u4e2d\u56fd\u6e38\u6cf3\u56fd\u5bb6\u961f\u961f\u957f\uff0c\u4f26\u6566\u5965\u8fd0\u3001\u91cc\u7ea6\u5965\u8fd0\u6e38\u6cf3\u51a0\u519b", "signature": "", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/aweme/100x100/93d2001446f25ee93b01.jpeg", "https://pb3.pstatp.com/aweme/100x100/93d2001446f25ee93b01.jpeg", "https://pb3.pstatp.com/aweme/100x100/93d2001446f25ee93b01.jpeg"], "uri": "93d2001446f25ee93b01"}, "nickname": "\u5b59\u6768", "unique_id": "", "follow_status": 1}, {"uid": "75256161971", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/thumb/78c00009fc4dbfbf3511"], "uri": ""}, "nickname": "\u6211\u5c31\u53eb \u8f89\u725b", "unique_id": "", "follow_status": 1}, {"uid": "59024473101", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u610f\u4e2d\u4eba\u662f\u6761\u72d7\u2665\ufe0f\u7eaf\u79cd\u4e0d\u62c6\u5bb6\uff01\n\u4f5b\u7cfb\u73a9\u5bb6 \u5206\u4eab\u65e5\u5e38\u751f\u6d3b\n\u7231\u72ac\uff1a\u5c0f\u54c8\u2665\ufe0f\n\u56db\u5c81 \u6bcd\u54c8", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/aweme/100x100/b70c001de1e0003b08a9.jpeg", "https://pb3.pstatp.com/aweme/100x100/b70c001de1e0003b08a9.jpeg", "https://pb3.pstatp.com/aweme/100x100/b70c001de1e0003b08a9.jpeg"], "uri": "b70c001de1e0003b08a9"}, "nickname": "\u6700\u840c\u54c8\u58eb\u5947", "unique_id": "mzxlkx", "follow_status": 1}, {"uid": "61460275159", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u628a\u597d\u73a9\u7684\u7b11\u8bdd\u518d\u7528\u5c0f\u53ef\u7231\u4eec\u6f14\u4e00\u6f14  V\u2764\ufe0f\uff1aakuaicoco", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/90f00018df6c60748a55.jpeg", "https://pb9.pstatp.com/aweme/100x100/90f00018df6c60748a55.jpeg", "https://pb3.pstatp.com/aweme/100x100/90f00018df6c60748a55.jpeg"], "uri": "90f00018df6c60748a55"}, "nickname": "\u963f\u9177\u7c91\u7c91", "unique_id": "", "follow_status": 1}, {"uid": "60628565069", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u514b\u62c9\u9ebb\u9ebb", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/5fe1000095f6f8623b7a.jpeg", "https://pb9.pstatp.com/aweme/100x100/5fe1000095f6f8623b7a.jpeg", "https://pb3.pstatp.com/aweme/100x100/5fe1000095f6f8623b7a.jpeg"], "uri": "5fe1000095f6f8623b7a"}, "nickname": "\u4e95\u8c46", "unique_id": "", "follow_status": 1}, {"uid": "100320015390", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p0.pstatp.com/origin/3791/5035712059"], "uri": ""}, "nickname": "\u7528\u62379420917364323", "unique_id": "", "follow_status": 2}, {"uid": "72308772499", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/aweme/100x100/c16000003f97583dac4.jpeg", "https://pb3.pstatp.com/aweme/100x100/c16000003f97583dac4.jpeg", "https://pb3.pstatp.com/aweme/100x100/c16000003f97583dac4.jpeg"], "uri": "c16000003f97583dac4"}, "nickname": "\u5df2\u91cd\u7f6e", "unique_id": "", "follow_status": 1}, {"uid": "59796199956", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/thumb/1dcc0011969d5eae291a"], "uri": ""}, "nickname": "blueberry\u8212\u8299\u857e", "unique_id": "", "follow_status": 1}, {"uid": "73757143338", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/aweme/100x100/7e600004ca3323a2f3e4.jpeg", "https://pb3.pstatp.com/aweme/100x100/7e600004ca3323a2f3e4.jpeg", "https://pb3.pstatp.com/aweme/100x100/7e600004ca3323a2f3e4.jpeg"], "uri": "7e600004ca3323a2f3e4"}, "nickname": "FM101.1", "unique_id": "", "follow_status": 1}, {"uid": "96895586940", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u6211\u662f\u4e00\u4e2a\ud83d\udc7a\uff01VX\uff1aPiscesBZ310", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/8d890003e13e3553d26a.jpeg", "https://pb9.pstatp.com/aweme/100x100/8d890003e13e3553d26a.jpeg", "https://pb3.pstatp.com/aweme/100x100/8d890003e13e3553d26a.jpeg"], "uri": "8d890003e13e3553d26a"}, "nickname": "\u86ee\u86ee", "unique_id": "RebornByf", "follow_status": 1}, {"uid": "60944492575", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u91cd\u5251\u65e0\u950b\uff0c\u5927\u5de7\u4e0d\u5de5", "avatar_thumb": {"url_list": ["https://p1.pstatp.com/obj/8e3e000f592d749cfd46"], "uri": ""}, "nickname": "\u5e02\u4e95\u5c0f\u6c11", "unique_id": "", "follow_status": 1}, {"uid": "63011452574", "weibo_verify": "", "remark_name": null, "verification_type": 1, "enterprise_verify_reason": "", "custom_verify": "", "signature": "\u4e00\u8d77\u53bb\u6d41\u6d6a", "avatar_thumb": {"url_list": ["https://p3.pstatp.com/aweme/100x100/bdd1001caa97706df46f.jpeg", "https://pb9.pstatp.com/aweme/100x100/bdd1001caa97706df46f.jpeg", "https://pb3.pstatp.com/aweme/100x100/bdd1001caa97706df46f.jpeg"], "uri": "bdd1001caa97706df46f"}, "nickname": "\u4e03\u4e03", "unique_id": "424242liu", "follow_status": 1}], "next_req_count": 5000, "max_time": 1534055716, "min_time": 1529037113}
     * @param okHttpClient
     * @param dyUserEntity
     * @return 成功返回impr_id 失败返回 "1"
     */
    String awemeV1SpotlightRelation(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);

    // https://aweme.snssdk.com/aweme/v1/abtest/param/?ts=1540353337&app_type=normal&os_api=25&device_platform=android&device_type=Redmi%204X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353337066&as=a1a50edc19937b6dff4355&cp=e039bf5f95fcc4d5e1%5DcKg&mas=018db2da3f5dcb08a060f64bcd28dec467acaccc2c6666266c46ec HTTP/1.1

    /**
     *
     * https://aweme.snssdk.com/aweme/v1/abtest/param/?ts=1540353337&app_type=normal&os_api=25&device_platform=android&device_type=Redmi%204X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353337066&as=a1a50edc19937b6dff4355&cp=e039bf5f95fcc4d5e1%5DcKg&mas=018db2da3f5dcb08a060f64bcd28dec467acaccc2c6666266c46ec HTTP/1.1
     * @param okHttpClient
     * @param dyUserEntity
     * @return 成功返回impr_id 失败返回 "1"
     */
    String awemeV1AbtestParam(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);



    /**
     * https://aweme.snssdk.com/aweme/v2/platform/share/settings/?ts=1540353337&app_type=normal&os_api=25&device_platform=android&device_type=Redmi%204X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353337065&as=a1053e0c89d35b4d8f4355&cp=ef35b35198f2c0d1e1QcYg&mas=017c42a56e47151ab07a0c3db92af8a233acaccc2c661c262c46ac HTTP/1.1
     * @param okHttpClient
     * @param dyUserEntity
     * @return 成功返回impr_id 失败返回 "1"
     */
    String awemeV2platformShareSettings(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);


    /**
     * https://aweme.snssdk.com/aweme/v1/check/in/?retry_type=no_retry&iid=47106823699&device_id=57616910195&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&openudid=cd5deef67704a09e&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540353337325&ts=1540353337&as=a1b52e6cd9934b4dcf4355&cp=ea34b85c97f0c0d2e1KqSu&mas=018d40c0db2d7bb19f65f7d3a8fa76763cacaccc2c66c6262c462c HTTP/1.1
     * @param okHttpClient
     * @param dyUserEntity
     * @return 成功返回impr_id 失败返回 "1"
     */
    String awemeV1CheckIn(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);


    // https://aweme.snssdk.com/aweme/v1/license/?retry_type=no_retry&iid=47106823699&device_id=57616910195&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&openudid=cd5deef67704a09e&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540353337412&ts=1540353337&as=a1655e0c39c39bcd9f4355&cp=e63eb95c9ffbc2d7e1%5BkIo&mas=012cfd2ab433796249f329685b6c6bead5acaccc2c669c26c6469c HTTP/1.1

    /**
     * TODO 生成了一串密钥，并不知道是什么，2个base64加密的东西
     * https://aweme.snssdk.com/aweme/v1/license/?retry_type=no_retry&iid=47106823699&device_id=57616910195&ac=wifi&channel=meizu&aid=1128&app_name=aweme&version_code=270&version_name=2.7.0&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&openudid=cd5deef67704a09e&manifest_version_code=270&resolution=720*1280&dpi=320&update_version_code=2702&_rticket=1540353337412&ts=1540353337&as=a1655e0c39c39bcd9f4355&cp=e63eb95c9ffbc2d7e1%5BkIo&mas=012cfd2ab433796249f329685b6c6bead5acaccc2c669c26c6469c HTTP/1.1
     * @param okHttpClient
     * @param dyUserEntity
     * @return
     */
    String awemeV1License(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);



    /**
     *
     * https://aweme.snssdk.com/aweme/v1/aweme/stats/?os_api=25&device_platform=android&device_type=Redmi+4X&iid=47106823699&ssmix=a&manifest_version_code=270&dpi=320&version_code=270&app_name=aweme&version_name=2.7.0&openudid=cd5deef67704a09e&device_id=57616910195&resolution=720*1280&os_version=7.1.2&language=zh&device_brand=Xiaomi&ac=wifi&update_version_code=2702&aid=1128&channel=meizu&_rticket=1540353337601&ts=1540353338&as=a1152edc1ac33badff4355&cp=ec37bc58adf9c1d7e1Uo%5Dw&mas=011b9073fbce39e7f726cb1d05fb8a796bacaccc2c6666268646cc HTTP/1.1
     * {"status_code":0,"extra":{"now":1540353338000},"log_pb":{"impr_id":"20181024115538010015065224600B0B"}}
     * @param okHttpClient
     * @param dyUserEntity
     * @return
     */
    String awemeV1AwemeStats(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);




}

