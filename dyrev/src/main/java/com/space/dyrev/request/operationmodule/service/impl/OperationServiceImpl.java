package com.space.dyrev.request.operationmodule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.request.operationmodule.params.DiggParams;
import com.space.dyrev.request.operationmodule.params.FollowParams;
import com.space.dyrev.request.operationmodule.service.OperationService;
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
}
