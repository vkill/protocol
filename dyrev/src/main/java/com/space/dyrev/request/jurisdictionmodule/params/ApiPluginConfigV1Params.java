package com.space.dyrev.request.jurisdictionmodule.params;

import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.request.util.CommonParams;
import com.space.dyrev.request.util.CommonUrlPart;

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
 *        @Date: 2018/10/24 22:25
 *        @Description: https://is.snssdk.com/api/plugin/config/v1/?
 **/
public class ApiPluginConfigV1Params {


    private static final String HOST = "is.snssdk.com";

    private static final String FUNC = "/api/plugin/config/v1/?";


    /**
     * 构造url
     * @param dyUserEntity
     * @return
     */
    public static String constructUrl(DyUserEntity dyUserEntity) {
        StringBuffer url = new StringBuffer("https://" + HOST + FUNC);
        url.append(CommonUrlPart.deviceUrl(dyUserEntity.getDevice()));
        return url.toString();
    }

    /**
     * 构造header
     * @param dyUserEntity
     * @return
     */
    public static Map constructHeader(DyUserEntity dyUserEntity) {

        return null;
    }

    /**
     *
     * @param dyUserEntity
     * @return
     */
    public static Map constructBody(DyUserEntity dyUserEntity) {

        return null;
    }


    // TODO main函数
    public static void main(String[] args) {
        DyUserEntity user = SaveAcc.getUser();
        String s = constructUrl(user);
        System.out.println(s);
    }
}
