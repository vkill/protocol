package com.space.dyrev.request.operationmodule.service;

import com.space.dyrev.commonentity.DyUserEntity;
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
 *        @Date: 2018/10/24 19:26
 *        @Description:
 **/
public interface OperationService {


    /**
     * 点赞的函数
     * https://aweme.snssdk.com/aweme/v1/commit/item/digg？
     * @param dyUserEntity 包括设备信息的实体类
     * @param awemeId 视频id
     * @return 点赞失败返回字符串1
     */
    String digg(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String awemeId);

    /**
     * 关注的函数
     * https://aweme.snssdk.com/aweme/v1/commit/follow/user/?
     * @param okHttpClient http对象
     * @param dyUserEntity 帐号实体类
     * @param userId 关注id
     * @return 关注成功返回imprid 失败返回 1
     */
    String follow(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String userId);


    /**
     * 修改个人信息
     * https://api.amemv.com/aweme/v1/commit/user/?
     * @param okHttpClient http对象
     * @param dyUserEntity 帐号实体类
     * @return
     */
    String modify(OkHttpClient okHttpClient, DyUserEntity dyUserEntity);

    /**
     * 搜索用户
     * https://aweme.snssdk.com/aweme/v1/user/?user_id=83908293618&ts=1540733910&app_type=normal&os_api=23&device_type=Redmi%204X&device_platform=android&ssmix=a&iid=46777879533&manifest_version_code=270&dpi=320&uuid=866709039379945&version_code=270&app_name=aweme&version_name=2.7.0&openudid=3e05931eec1a90af&device_id=58306217792&resolution=720*1280&os_version=6.0.1&language=zh&device_brand=Xiaomi&ac=mobile&update_version_code=2702&aid=1128&channel=wandoujia&_rticket=1540733912797&as=a165bb8d36cd8bdb354355&cp=b5d3b9586058d6b9e1OoWw&mas=01c8d4152976e2d9b661e30acf749710d9acaccc2caccc4626461c HTTP/1.1
     * @param okHttpClient
     * @param userId
     */
    void searchUser(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String userId);


    /**
     * 用密码登陆
     * https://is.snssdk.com/passport/mobile/login/?os_api=23&device_type=Redmi+4X&device_platform=android&ssmix=a&iid=46777879533&manifest_version_code=270&dpi=320&uuid=866709038462304&version_code=270&app_name=aweme&version_name=2.7.0&openudid=3e05931eec1a90af&device_id=58306217792&resolution=720*1280&os_version=6.0.1&language=zh&device_brand=Xiaomi&ac=mobile&update_version_code=2702&aid=1128&channel=wandoujia&_rticket=1540803655251&ts=1540803653&as=a1a57cad35f47b3cf64355&cp=cd4cb75c516cdfcce1IgQk&mas=019217fc6d787cf4c211ec1dcc41c7a638acaccc2c6c66c6cc46ec HTTP/1.1
     * @param okHttpClient
     * @param dyUserEntity
     * @param captcha 如果为""或者 null则为空
     */
    DyUserEntity passportMobileLogin(OkHttpClient okHttpClient, DyUserEntity dyUserEntity, String captcha) throws Exception;
}
