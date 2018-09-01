package platform.tv;

import okhttp3.OkHttpClient;

/**
 * @program: protocol
 * @description: 包含cookie的http请求的注册实现类
 * @author: gaoxiang
 * @create: 2018-08-27 18:47
 **/
public class RegisterTv {

    //public RequestInfoGetter requestInfoGetter;
    public OkHttpClient client;

    public RegisterTv(){
        //requestInfoGetter = new RequestInfoGetter();
        //创建http服务器
        client = new OkHttpClient.Builder().build();
    }
    /**
    public boolean sendHttpByGet(RequestUrlPo requestUrlPo){
        Request.Builder builder = new Request.Builder();
        builder.url(requestUrlPo.getUrl());
        Map<String,String> header_Params = URLmakeTools.key_value_split(requestUrlPo.jsonInfo);
        for(Map.Entry<String, String> entry : header_Params.entrySet()){
            //System.out.println(entry.getKey()+"   "+entry.getValue());
            builder.addHeader(entry.getKey(), DataBaseTools.encodeHeadInfo(entry.getValue()));
        }
        // 构建GET请求，并设置对应的header内容
        Request request = builder.get().build();

// 执行GET请求，并在异步回调中处理请求
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("调用getHTTP 时发生错误："+ requestUrlPo.cookie);
        }

// 打印登录用户名，验证是否获取到了用户的登录信息(状态信息)
        if (response.isSuccessful()) {
            String content = null;
            try {
                content = response.body().string();
                System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("对返回结果转string出错");
            }
            System.out.println(content);
        }
        System.out.println(response);
        return  true;
    }

    public boolean sndHttpByPost(RequestUrlPo requestUrlPo){
        Request.Builder builder = new Request.Builder();
        builder.url(requestUrlPo.getUrl());
        //System.out.println(requestUrlPo.jsonInfo);
        //System.out.println("");
        //System.out.println(requestUrlPo.body);
        Map<String,String> header_Params = URLmakeTools.key_value_split(requestUrlPo.jsonInfo);
        for(Map.Entry<String, String> entry : header_Params.entrySet()){
            //System.out.println(entry.getKey()+"   "+entry.getValue());
            builder.addHeader(entry.getKey(), DataBaseTools.encodeHeadInfo(entry.getValue()));
        }

        Map<String,String> params = URLmakeTools.key_value_split(requestUrlPo.body);

        MultipartBody.Builder urlBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                if (params.get(key)!=null){
                    System.out.println(key+"    "+ params.get(key));
                    urlBuilder.addFormDataPart(key, params.get(key));
                }
                //urlBuilder.addFormDataPart(key, params.get(key));

            }
        }
// 构造Request->call->执行
        Request request = builder
                .post(urlBuilder.build())//参数放在body体里
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("调用getHTTP 时发生错误："+ requestUrlPo.cookie);
        }
        String content = null;
        try {
            content = response.body().string();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("对返回结果转string出错");
        }
        System.out.println(content);

       // System.out.println(request);
       // System.out.println();
       // System.out.println(request.body().toString());
       // System.out.println();
       // System.out.println(request.headers());
        return false;
    }

    public void testHttpPost(){

    }


    public static void main(String[]args){
        RegisterTv registerTv = new RegisterTv();
        //RequestUrlPo requestUrlPo = registerTv.requestInfoGetter.getAllSimulation("53513398877").get(BaseRequestTpyeInfo.registerMessage);
        //registerTv.sndHttpByPost(requestUrlPo);
        //registerTv.sendHttpByGet(requestUrlPo);
        /**
        System.out.println(requestUrlPo.cookie);
        Iterator<Map.Entry<Integer,String>> it = key_value_split(requestUrlPo.jsonInfo).entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,String> entry=it.next();
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        **/

}
