package com.space.dyrev.testpackage;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.request.operationmodule.params.DiggParams;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.request.operationmodule.service.impl.OperationServiceImpl;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 *        @Date: 2018/10/24 17:45
 *        @Description: 
 **/
public class TestLogin {

    private static final Logger logger = LoggerFactory.getLogger(TestLogin.class);

    private static void testSplitUserCookie() {
//        String userCookies = "{\"sid_guard\":\"6e23275781583a41980cadb21a35160d%7C1540374209%7C5184000%7CSun%2C+23-Dec-2018+09%3A43%3A29+GMT\",\"uid_tt\":\"1a1c02258911a3910a9eb3cad048c693\",\"sid_tt\":\"6e23275781583a41980cadb21a35160d\",\"odin_tt\":\"21409bf35e6d71a945a34b989ce6cd8e8a5945afb4cf7f633b9cdf635e1f0366b65e5cb4067357dd44a836650ee3663d\",\"sessionid\":\"6e23275781583a41980cadb21a35160d\"}";
//        JSONObject jsonObject = JSONObject.parseObject(userCookies);
//        DyUserEntity dyUserEntity = new DyUserEntity();
//        dyUserEntity.setAccount("18801589728");
//        dyUserEntity.setSessionKey(jsonObject.getString("sessionid"));
//        dyUserEntity.setUserCookies(userCookies);
//        dyUserEntity.setUserId();
    }

    /**
     * 测试点赞
     * @param dyUserEntity
     * @param okHttpClient
     */
    private static void testDigg(DyUserEntity dyUserEntity, OkHttpClient okHttpClient) {
        String digg = os.digg(okHttpClient, dyUserEntity, "6615732004694527246");
        logger.info("点赞结果：" + digg);
    }

    private static void testFollow(DyUserEntity dyUserEntity, OkHttpClient okHttpClient) {
        String follow = os.follow(okHttpClient, dyUserEntity, "91713332648");
        logger.info("关注结果：" + follow);
    }

    private static OperationService os = new OperationServiceImpl();

    public static void main(String[] args) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        DyUserEntity dyUserEntity = JSONObject.parseObject(SaveAcc.USER_STRING).toJavaObject(DyUserEntity.class);
////        testDigg(dyUserEntity, okHttpClient);
//        testFollow(dyUserEntity, okHttpClient);


//        String result = "{\"status_code\":0,\"extra\":{\"now\":1540389276530,\"fatal_item_ids\":[],\"logid\":\"20181024215436010015075156426905\"},\"follow_status\":1,\"status_msg\":\"关注成功\",\"log_pb\":{\"impr_id\":\"20181024215436010015075156426905\"}}";
//
//        JSONObject msg = JSONObject.parseObject(result);
//
//        if (msg.getString("status_code")!=null && msg.getString("status_code").equals("0")) {
//            if (msg.getString("status_msg")!=null && msg.getString("status_msg").equals("关注成功")) {
//                logger.info("关注结果impr_id -> msg={}", msg.getJSONObject("log_pb").getString("impr_id"));
//
//            } else  {
//                logger.error("没有找到相关的impr_id");
//            }
//        } else {
//            logger.error("关注返回数据查找status_code失败");
//        }
    }
}
