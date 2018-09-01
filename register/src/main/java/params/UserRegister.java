package params;
import okhttp3.Request;
import params.tools.ConstructPostRequest;
import params.tools.ConstructRequestUrl;
import po.RequestTokenVo;

import java.io.IOException;
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
    public Request RequestToRegister(){

        //需要输入的参数待定，之后添加
        String host = "";
        String requestMsg = "";
        Map<String, String> requestToken = null;

        //调用了tool包中的constructUrl方法来生成url，需要输入参数host，请求地址requestedMsg以及传输键值对requestToken
        String url = new ConstructRequestUrl().constructUrl(host, requestMsg, requestToken);

        Map <String, String> header = null;
        Map <String, String> body = null;

        //创建Request的实体对象
        RequestTokenVo requestEntity1 = new RequestTokenVo(url, header, body);

        //调用请求发送验证码
        Request request1 = RequestToSendCode(requestEntity1);
        //通过上面获取的request1修改下面需要发送的requestEntity2
        RequestTokenVo requestEntity2 = new RequestTokenVo(url, header, body);

        //调用确认验证码
        Request request2 = RequestToSendCode(requestEntity2);

        return null;
    }

    /**
     * 注册时对发送验证吗的请求
     * @param requestEntity 创建的保存请求信息的实体对象
     * @return
     */
    public Request RequestToSendCode(RequestTokenVo requestEntity){

        //调用post请求方法，构建post请求来请求发送验证码
        Request request = null;
        try {
            request = new ConstructPostRequest().construct(requestEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public Request RequestToRegister(RequestTokenVo requestEntity){

        //调用post请求方法，构建post请求来进行验证码确认
        Request request = null;
        try {
            request = new ConstructPostRequest().construct(requestEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }
}
