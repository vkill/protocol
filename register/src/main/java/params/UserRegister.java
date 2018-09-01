package params;
import okhttp3.*;
import params.tools.ConstructPostRequest;
import params.tools.ConstructRequestUrl;
import platform.email.EmailGetter;
import po.PhonePo;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @program: register
 * @description: 模拟用户进行注册的类
 * @author: Mr.Jia
 * @create: 2018-09-01 15:42
 **/
public class UserRegister {


    /**
     * UserRegister类中整体管理调用的方法
     * @return
     */
    public static Request RequestToRegister(){

//        //调用短信平台获取验证码
//        EmailGetter emailGetter =new EmailGetter();
//        emailGetter.loginIT();
//        while(true){
//            PhonePo phonePo =emailGetter.getPhoneNumber();
//            System.out.print(emailGetter.getIdentCode(phonePo.getP_ID()));
//            break;
//        }


        //需要输入的参数待定，之后添加
        String host = "";
        String requestMsg = "";
        Map<String, String> requestToken = null;
        Map <String, String> header = null;
        Map <String, String> body = null;

        //调用了tool包中的constructUrl方法来生成url，需要输入参数host，请求地址requestedMsg以及传输键值对requestToken
        String url = new ConstructRequestUrl().constructUrl(host, requestMsg, requestToken);

        //创建Request的实体对象
        RequestTokenVo requestEntity1 = new RequestTokenVo(url, header, body);

        //调用请求发送验证码
        Request request1 = RequestToSendCode(requestEntity1);
        //通过上面获取的request1修改下面需要发送的requestEntity2
        RequestTokenVo requestEntity2 = new RequestTokenVo(url, header, body);

        //发送请求
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call1 = okHttpClient.newCall(request1);
        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                System.out.println("响应成功,已经成功发送验证码");
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });

        //调用确认验证码
        Request request2 = RequestToSendCode(requestEntity2);

        return null;
    }

    /**
     * 注册时对发送验证码的请求
     * @param requestEntity 创建的保存请求信息的实体对象
     * @return
     */
    public static Request RequestToSendCode(RequestTokenVo requestEntity){

        //调用post请求方法，构建post请求来请求发送验证码
        Request request = null;
        try {
            request = new ConstructPostRequest().construct(requestEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    /**
     * 注册时确认验证码时发送的请求
     * @param requestEntity
     * @return
     */
    public static Request RequestToRegister(RequestTokenVo requestEntity){

        //调用post请求方法，构建post请求来进行验证码确认
        Request request = null;
        try {
            request = new ConstructPostRequest().construct(requestEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static void main(String[] args) {
        RequestToRegister();

    }
}
