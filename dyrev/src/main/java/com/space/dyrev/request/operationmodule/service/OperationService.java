package com.space.dyrev.request.operationmodule.service;

import com.space.dyrev.commonentity.DyUserEntity;
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
}
