package params.tools;

import okhttp3.Request;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.Map;

/**
 * @program: register
 * @description: 构造get请求的基础方法类
 * @author: Mr.Jia
 * @create: 2018-09-01 14:15
 **/
public class ConstructGetRequest {

    public Request construct(RequestTokenVo requestEntity) throws IOException {

        // 构建GET请求，并设置请求消息头
        // requestEntity中包含三部分，Url、Header，body为空
        Request.Builder builder = new Request.Builder();
        builder.url(requestEntity.getUrl());
        Map<String,String> headerParams = requestEntity.getHeader();
        for(String key : headerParams.keySet()){
            builder.addHeader(key, headerParams.get(key));
        }
        Request request = builder.get().build();


        return request;
    }
}
