package com.space.dyrev.apisupport.codeapi;

import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

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
 *        @Date: 2018/10/26 22:07
 *        @Description: 
 **/
public class DaShiZiCodeApi {

    private static Logger logger = LoggerFactory.getLogger(DaShiZiCodeApi.class);

    private String userName ="api_mmg0088_crdl";
    private String password ="aa977525";
    private String projectID ="1055";
    private String loginUrl ;
    public String  usertoken ="4E4F35F081EA4724939AC04D5D7201E4";
    private String errorStr = "ERR";
    private String successStr = "OK";

    public static DaShiZiCodeApi daShiZiCodeApi = new DaShiZiCodeApi();

    public static DaShiZiCodeApi getInstrance(){
        return daShiZiCodeApi;
    }

    private DaShiZiCodeApi(){
        loginIT();
    }

    private void loginIT(){
        if (daShiZiCodeApi==null) {
            loginIT(userName,password);
        }
    }

//    private void loginIT(String user,String password){
//        this.userName = user;
//        this.password = password;
//    }

    private void loginIT(String user,String password){
        this.userName = user;
        this.password = password;
        String tag = errorStr;
        int errTime =0;
        loginUrl = "http://118.178.232.250:18003/yhapi.ashx?Action=userLogin&userName="+userName+"&userPassword="+password;
        while (tag.equals(errorStr)&&errTime<3){

            RequestEntity req = new RequestEntity(RequestEnum.GET);
            req.setUrl(loginUrl);
            req.setOkHttpClient(new OkHttpClient());

            try {
                Response response = OkHttpTool.handleHttpReq(req);

                String result = response.body().string();

                String[] split = result.split("\\|");


                if (split[0]!= null && split[0].equals("OK")) {
                    logger.info(" ----- 大狮子短信平台登陆结果 ----- -> token = {}", split[1]);
                    usertoken = split[1];
                    break;
                } else {
                    errTime++;
                    if (errTime > 3) {
                        logger.error(" ----- 短信平台出错 ----- -> 尝试次数 = {} 次", errTime);
                        break;
                    }
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public PhoneEntity getPhoneNumber(OkHttpClient okhttpclient) throws Exception {
        return getPhoneNumber("随机", okhttpclient);

    }

    public PhoneEntity getPhoneNumber(String phoneNum, OkHttpClient okhttpclient) throws Exception {
        String tag =errorStr;
        String phone_url;
        if(phoneNum.equals("随机")){
            //http://118.178.232.250:18003/yhapi.ashx?Action=getPhone&token=token&i_id=项目ID&d_id=&p_operator=&p_qcellcore=&mobile=
            phone_url="http://118.178.232.250:18003/yhapi.ashx?Action=getPhone&token="+usertoken+"&i_id="+projectID+"&d_id=&p_operator=&p_qcellcore=&mobile=";
        }else{
            phone_url="http://118.178.232.250:18003/yhapi.ashx?Action=getPhone&token="+usertoken+"&i_id="+projectID+"&d_id=&p_operator=&p_qcellcore=&mobile="+phoneNum;
        }
        Response response = null;
        String[] buffers = null;
        int worryTime =0;
        while(tag.equals(errorStr)&&worryTime<2){
            Request.Builder builder = new Request.Builder();
            builder.url(phone_url);
            Request request = builder.get().build();
            response = okhttpclient.newCall(request).execute();

            String buff= null;

            buff = response.body().string();

            buffers = buff.split("\\|");
            tag =buffers[0];
            if(tag.equals(successStr)){
                logger.error(" ----- 成功获取手机号 ----- -> number = {} ", buffers[4]);
//                System.out.println("成功获取手机号: "+buffers[4]+" "+buffers[1]+" "+buffers[6]);
                break;
            }else{
                logger.error(" ----- 短信平台出现故障 ----- -> 原因 = {} ----- 处理情况 = {}", buff, "等待10秒");
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            worryTime++;
        }

        if(worryTime==2){
            logger.error(" ----- 短信平台出现故障 ----- -> 原因 = {}", "平台没有更多的帐号");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new Exception();
//            return null;
        }else{

            PhoneEntity phoneEntity = new PhoneEntity(buffers[1],buffers[2],buffers[3],buffers[4],"+86");
            return phoneEntity;
        }
    }

    public String getIdentCode(PhoneEntity phoneEntity, OkHttpClient okhttpclient) throws IOException {
        //http://118.178.232.250:18003/yhapi.ashx?Action=getPhoneMessage&token=token&p_id=取号接口返回的P_ID
        String P_ID = phoneEntity.getPid();
        String infoUrl ="http://118.178.232.250:18003/yhapi.ashx?Action=getPhoneMessage&token="+usertoken+"&p_id="+P_ID;
        Response response = null;
        String buff = null;
        String[] buffers;
        String tag =errorStr;
        String result = 0+"";
        int buffer_Num =0;
        while(tag.equals(errorStr)&buffer_Num<14){
            try {
                Thread.sleep(5001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Request.Builder builder = new Request.Builder();
            builder.url(infoUrl);
            Request request = builder.get().build();
            response = okhttpclient.newCall(request).execute();
//                System.out.println(document);


            buff = response.body().string();
//            System.out.println(buff);

            buffers = buff.split("\\|");
            tag = buffers[0];
            result = buffers[1];
            if(tag.equals(successStr)){
                logger.info(" ----- 成功获取验证码 ----- -> 号码 = {}  验证码 = {}", phoneEntity.getPhoneNum(), buffers[1]);
                return buffers[1];
            }
            else if(result.equals("-4")){
                logger.error(" ----- 短信平台出现故障 ----- -> 原因 = {}", "号码已经强制释放");
                try {
                    Thread.sleep(100001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "1";
            }else{

            }
            buffer_Num++;
            logger.info(" ----- 获取验证码失败 ----- -> 号码 = {}  尝试次数 = {}", phoneEntity.getPhoneNum(), buffer_Num);
        }
        logger.error(" ----- 验证码获取出现故障 ----- -> 号码 = {}, 原因 = {}", phoneEntity.getPhoneNum(),"验证码获取失败");
        //smakePhoneBlank(P_ID);
        return "1";
    }

    public static void test() {
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.NO_PROXY);
        try {
//            PhoneEntity phoneNumber = DaShiZiCodeApi.getInstrance().getPhoneNumber(okhttpClient);
//            System.out.println(phoneNumber);
            PhoneEntity phoneNumber = DaShiZiCodeApi.getInstrance().getPhoneNumber( "18778606794",okhttpClient);
            DaShiZiCodeApi.getInstrance().getIdentCode(phoneNumber, okhttpClient);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        test();
    }
}
