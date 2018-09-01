package httpmaker;

import okhttp3.FormBody;
import okhttp3.Request;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.Map;

/**
 * @program: register
 * @description: 构造get请求的基础方法类
 * @author: Mr.Jia
 * @create: 2018-09-01 15:22
 **/
public class ConstructPostRequest {

    public static Request construct(RequestTokenVo requestEntity) throws IOException {

        // 构建POST请求，并设置请求消息头
        //requestEntity中包含三部分，Url、Header和Body
        FormBody.Builder formBody = new FormBody.Builder();     //创建表单请求体

        Request.Builder builder = new Request.Builder();
        builder.url(requestEntity.getUrl());
        Map<String, String> headerParams = requestEntity.getHeader();
        Map<String, String> bodyParams = requestEntity.getBody();
        for(String key : headerParams.keySet()){        //添加header信息
            builder.addHeader(key, headerParams.get(key));
        }
        for(String key : bodyParams.keySet()){      //添加body信息
            builder.post(formBody.build()).build();
        }
        Request request = builder.get().build();


        return request;
    }
}
