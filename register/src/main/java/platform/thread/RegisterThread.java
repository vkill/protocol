package platform.thread;

import com.space.register.configurer.Test;
import com.space.register.controller.DeviceController;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import enums.paramtable.DirTable;
import enums.paramtable.urltools.URLmakeTools;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import jsonreader.tools.JsonTableGetter;
import keytools.Crc32;
import keytools.ScretAES;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import params.ParamCreater;
import params.tools.RequestURLCreater;
import platform.email.EmailGetter;
import platform.main.DevicerAbleGetter;
import platform.main.DevicerAblePostter;
import platform.main.TvRegisterMaker;
import platform.main.UrlBodyCreaterTool;
import po.HostIPPo;
import po.PhonePo;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: protool
 * @description: 注册设备和用户使用到的线程实现类
 * @author: Mr.gao
 * @create: 2018-09-21 17:57
 **/
public class RegisterThread implements Runnable{

    Test test = Test.getInstrance();
    @Override
    public void run() {
        while(true){
//            HostIPPo hostIPPo = DeviceController.hostIpQuene.peek();
//            if(DeviceController.hostIpQuene.size()==4){
//                DeviceController.getNeedIPFromWeb();
//            }
            for(int i =0;i<6;i++){
                try {
                    oneUserInfo("",0);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean sendMessage(Request request, OkHttpClient okHttpClient){
        return false;
    }

    public boolean oneUserInfo(String host,int port) throws IOException {
        Map<String,String> kaoHeader = new HashMap<String,String>();
        Map<String,String> deviceMapBuff = new HashMap<String,String>();

        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
//
        DeviceEntity deviceEntity = tvRegisterMaker.registerUserToTv();
//                deviceRepository.getOne(11);

        //注册设备之后调用的get方法
        OkHttpClient okHttpClient = tvRegisterMaker.okHttpClient;
        //获取设备真实信息。
        JSONObject realDevice = null;
        Request requestUpload = null;
        Response response = null;
        String jsonString = null;
        JSONObject resultJson =null;
        //第一个v1 setting方法
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsOnlyHost(DirTable.v1_Settings_Hoster,DirTable.v1_Settings,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //第二个abtest param
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsOnlyHost(DirTable.abtest_Param_Hoster,DirTable.abtest_Param,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //第三个lucky money setting, 需要传递cookie
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.money_Settings_Hoster,DirTable.money_Settings,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //第诗歌lucky money new user，需要传递cookie
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.money_New_User_Hoster,DirTable.money_New_User,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
//        //第五个 theme package
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.theme_Package_Hoster,DirTable.theme_Package,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //第六个 live answer
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.live_Answer_Hoster,DirTable.live_Answer,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //第七个 rec new
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.rec_New_Hoster,DirTable.rec_New,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //第八个 app_alert
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.service_2_app_Alert_Hoster,DirTable.service_2_app_Alert,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
        //第九个 aweme v14
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.aweme_V14_Hoster,DirTable.aweme_V14,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        //获取odin_tt
        jsonString =GzipGetteer.uncompressToString(response.body().bytes());
        Headers headerhe = response.headers();
        ArrayList<String> strings = RequestURLCreater.getCookieFromResponseHeaders(RequestURLCreater.getStrCookie(headerhe));
        StringBuilder cookies = new StringBuilder();
        for(int i=0;i<strings.size();i++){
            if(i==strings.size()-1){
                cookies.append(strings.get(i));
                break;
            }
            cookies.append(strings.get(i)+";");
        }
        deviceEntity.setCookie(deviceEntity.getCookie()+";"+cookies.toString());
        //获取添加oddin_tt的cookie
        System.out.println(jsonString);
//        //第十个 screen ad
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.screen_Ad_Hoster,DirTable.screen_Ad,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
        //第十一个 setting v2
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.setting_V2_Hoster,DirTable.setting_V2,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
//        //第十二个 license
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.v1_License_Hoster,DirTable.v1_License,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);

        //第一个post body方法
        kaoHeader.clear();
        kaoHeader.put("Cookie",deviceEntity.getCookie()+";"+"qh[360]=1");
        kaoHeader.put("Content-Type","application/x-www-form-urlencoded");
        deviceMapBuff = deviceEntity.getDeviceMap();
        requestUpload = DevicerAblePostter.getRealDeviceRequsets(DirTable.cloudpush_Updata_Sender_Hoster,DirTable.cloudpush_Updata_Sender,DirTable.cloudpush_Updata_Sender_Body,kaoHeader,deviceMapBuff,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);

        //第十三个 lucky money sittings
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.money_Settings1_Hoster,DirTable.money_Settings,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //第十四个 lucky money new user
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.money_New_User1_Hoster,DirTable.money_New_User1,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
//        //第十五个 sdk log
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.sdk_Log_Hoster,DirTable.sdk_Log,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //第十六个 v1 feed
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.v1_feed_Hoster,DirTable.v1_feed,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
        //第十七个 private_message
        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.message_Account_Hoster,DirTable.message_Account,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //第十八个 feedback 暂未写

        realDevice = JsonTableGetter.contrustJsonForReal(deviceEntity);
        requestUpload = sendRealDeviceInfo(deviceEntity,realDevice,"cold_start");
        try {
            response = okHttpClient.newCall(requestUpload).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("更新设备失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("更新设备失败");
        }
        System.out.println(jsonString);
        deviceEntity.setDevice_upload_info_json(realDevice.toString());

        //第二个 post body 方法
        kaoHeader.clear();
        kaoHeader.put("Cookie",deviceEntity.getCookie()+";"+"qh[360]=1");
        kaoHeader.put("Content-Type","application/x-www-form-urlencoded");
        deviceMapBuff = deviceEntity.getDeviceMap();
        requestUpload = DevicerAblePostter.getRealDeviceRequsets(DirTable.v1_Aweme_Stats_Hoster,DirTable.v1_Aweme_Stats,DirTable.v1_Aweme_Stats_Body,kaoHeader,deviceMapBuff,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);

//        //第十九个 appmonitor settings
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.appmonitor_Settings_noSrckey_Hoster,DirTable.appmonitor_Settings_noSrckey,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //第二十个 app_notice_status
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsets(DirTable.app_notice_status_Hoster,DirTable.app_notice_status,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);

        //为了测试而添加的读取方法
        UrlRequestEntity urlRequestEntity= DeviceController.allUrl.get(0);
        UrlRequestEntity urlRequestEntity1 = DeviceController.allUrl.get(1);
        EmailGetter emailGetter = new EmailGetter();
        emailGetter.loginIT();
        PhonePo phonePo = null ;
        Request request = null;
        String code = null;
        int kao = 0;
        while(kao<5){
            phonePo = emailGetter.getPhoneNumber();
            request = tvRegisterMaker.sendMessageForRegister(urlRequestEntity,deviceEntity,phonePo,"");
            try {
                response = okHttpClient.newCall(request).execute();
                jsonString = GzipGetteer.uncompressToString(response.body().bytes());
                resultJson = new JSONObject(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("发送验证码失败");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发送验证码失败");
            }
            System.out.println(jsonString);
            //获取验证码
            code = emailGetter.getIdentCode(phonePo.getP_ID());
            if(code.equals("请求超时")){
                continue;
            }else{
                break;
            }
        }
        //获取设备真实信息。
        try {
            realDevice.put("time",Long.parseLong(ParamCreater.get_Rticket()));
        } catch (JSONException e) {
            System.out.println("json插入新值出错");
            e.printStackTrace();
        }

        requestUpload = sendRealDeviceInfo(deviceEntity,realDevice,"login");
        try {
            response = okHttpClient.newCall(requestUpload).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送验证码后更新设备失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送验证码后更新设备失败");
        }
        System.out.println(jsonString);
        Request request1 = tvRegisterMaker.sendMessageForRegister(urlRequestEntity1,deviceEntity,phonePo,code);
        Headers headers = null;
        try {
            response = okHttpClient.newCall(request1).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
            headers = response.headers();
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("注册用户结果获取失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册用户结果获取失败");
        }
        DYUserEntity dyUserEntity = new DYUserEntity();
        dyUserEntity.setBelong("ours");
        dyUserEntity.setName("呵呵哒哒");
        dyUserEntity.setPassword("asd123456");
        dyUserEntity.setPhoneArea("66");
        dyUserEntity.setPhoneNum(phonePo.getPhone_Num());
        //dyUserEntity.setSimulationID(deviceEntity.getId()+"");
        try {
            dyUserEntity.setUid(resultJson.getJSONObject("data").get("user_id").toString());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("读取data json 报错");
        }
        strings = RequestURLCreater.getCookieFromResponseHeaders(RequestURLCreater.getStrCookie(headers));
        cookies = new StringBuilder();
        int z =0;
        for(int i=0;i<strings.size();i++){
            if(i==strings.size()-1){
                cookies.append(strings.get(i));
                break;
            }
            if(strings.get(i).startsWith("odin_tt")&z==0){
                z++;
                continue;
            }
            cookies.append(strings.get(i)+";");
        }
        dyUserEntity.setUserCookie(cookies.toString());

        //此处构造包含不同信息的header 中cookie 请求
        Map<String,String> headWithCookie = new HashMap<String,String>();
        StringBuilder headsCookies = new StringBuilder();
        String[] headscookies = deviceEntity.getCookie().split(";");
        for(int i =0;i<headscookies.length;i++){
            if(headscookies[i].startsWith("odin_tt")){
                continue;
            }
            headsCookies.append(headscookies[i]+";");
        }
        headsCookies.append(dyUserEntity.getUserCookie());
        headWithCookie.put("Cookie",headsCookies.toString());
        Map<String,String> onlyUserCookie = new HashMap<String,String>();
        onlyUserCookie.put("Cookie",dyUserEntity.getUserCookie());
        //此处构造额外的设备信息
        Map<String,String> needRealInfo = deviceEntity.getDeviceMap();
        needRealInfo.put("client_uid",dyUserEntity.getUid());
        needRealInfo.put("phone_number","+"+dyUserEntity.getPhoneArea()+dyUserEntity.getPhoneNum());
        needRealInfo.put("user_id",dyUserEntity.getUid());
        //此处获取第一个权限，调用的方法为/v1/friend/register/notice/
        Request request2 =DevicerAblePostter.getRealDeviceRequsets(DirTable.friend_Register_Hoster,DirTable.friend_Register,DirTable.friend_Register_Body,headWithCookie,needRealInfo,true);
        try {
            response = okHttpClient.newCall(request2).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("用户权限获取失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户权限获取失败");
        }
        //此处为获取权限测试的第二处请求，调取的方法为：/v1/abtest/param/
        request2 =DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.abtest_Param_Hoster,DirTable.abtest_Param,headWithCookie,deviceEntity,true);
        try {
            response = okHttpClient.newCall(request2).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("运行参数测试失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("运行参数测试失败");
        }
        //此处为获取权限的第四处请求，调取方法为：v1/check/in/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.cheak_In_Hoster,DirTable.check_In_Str,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第三处请求，调取方法为：/v1/settings/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.v1_Settings_User_Hoster,DirTable.v1_Settings_User,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第七处请求，调取方法为：v1/lucky/money/settings/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.money_Settings1_Hoster,DirTable.money_Settings1,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第五处请求，调取方法为：/aweme/v1/user/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.v1_User_Str_Hoster,DirTable.v1_User_Str,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第四处请求，调取方法为：v1/check/in/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.cheak_In_Hoster,DirTable.check_In_Str,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第六处请求，调取方法为：/aweme/v1/lucky/money/newuser/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.money_New_User1_Hoster,DirTable.money_New_User1,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第五处请求，调取方法为：/aweme/v1/user/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.v1_User_Str_Hoster,DirTable.v1_User_Str,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
//        //此处为获取权限的额外请求，调取方法为：aweme/v1/license
//        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.v1_License_Hoster,DirTable.v1_License,headWithCookie,deviceEntity,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //此处为获取权限的第十四处请求，调取方法为：/v1/story/list/
//        requestUpload = DevicerAbleGetter.getALLUsageRequest(DirTable.v1_Story_List_Hoster,DirTable.v1_Story_List,headWithCookie,needRealInfo,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
        //此处为获取权限的第十五处请求，调取方法为：/service/settings/v2/
//        requestUpload = DevicerAbleGetter.getALLUsageRequest(DirTable.setting_V2_Hoster,DirTable.setting_V2,headWithCookie,needRealInfo,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
//        //此处为获取权限的第十五处请求，调取方法为：/aweme/v1/story/
//        requestUpload = DevicerAbleGetter.getALLUsageRequest(DirTable.v1_Story_Str_Hoster,DirTable.v1_Story_Str,headWithCookie,needRealInfo,true);
//        response = okHttpClient.newCall(requestUpload).execute();
//        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
//        System.out.println(jsonString);
        //此处为获取权限的第六处请求，调取方法为：/v1/spotlight/relation/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.spotlight_relation_Hoster,DirTable.spotlight_relation,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取权限的第十处请求，调取方法为：private_message/account/login_notify/
        requestUpload = DevicerAbleGetter.getALLUsageRequest(DirTable.private_message_account_logout_notify_Hoster,DirTable.private_message_account_logout_notify,headWithCookie,needRealInfo,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);
        //此处为获取点赞权限的第十三次请求，调取的方法为：im/chatlist/
        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.im_Chatlist_Hoster,DirTable.im_Chatlist,headWithCookie,deviceEntity,true);
        response = okHttpClient.newCall(requestUpload).execute();
        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
        System.out.println(jsonString);




//        //此处为获取权限的第六处请求，调取方法为：/aweme/v1/lucky/money/newuser/
////        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.money_New_User1_Hoster,DirTable.money_New_User1,onlyUserCookie,deviceEntity,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);
////
////        //此处为获取权限的第八处请求，调取方法为：aweme/v1/check/in/
////        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.cheak_In_Hoster,DirTable.check_In_Str,onlyUserCookie,deviceEntity,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);
////        //此处为获取权限的第九处请求，调取方法为：v1/check/in/
////        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.cheak_In_Hoster,DirTable.check_In_Str,onlyUserCookie,deviceEntity,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);
////
////
////        //此处为获取权限的第十处请求，调取方法为：private_message/account/login_notify/
////        requestUpload = DevicerAbleGetter.getALLUsageRequest(DirTable.private_message_account_logout_notify_Hoster,DirTable.private_message_account_logout_notify,onlyUserCookie,needRealInfo,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);
////
////        //此处为获取权限的第十一处请求，调取方法为：/aweme/v1/story/
////        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.v1_Story_Str_Hoster,DirTable.v1_Story_Str,onlyUserCookie,deviceEntity,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);
////
////        //此处为获取权限的第十二处请求，调取方法为：/aweme/v1/storyList/
////        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.v1_Story_List_Hoster,DirTable.v1_Story_List,onlyUserCookie,deviceEntity,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);
////        //此处为获取点赞权限的第十三次请求，调取的方法为：im/chatlist/
////        requestUpload = DevicerAbleGetter.getRealDeviceRequsetsWithHeads(DirTable.im_Chatlist_Hoster,DirTable.im_Chatlist,onlyUserCookie,deviceEntity,true);
////        response = okHttpClient.newCall(requestUpload).execute();
////        jsonString = GzipGetteer.uncompressToString(response.body().bytes());
////        System.out.println(jsonString);

        deviceEntity = test.saveDevice(deviceEntity);
        dyUserEntity.setSimulationID(deviceEntity.getId()+"");
        test.saveUser(dyUserEntity);
        return  true;

    }

    public Request sendRealDeviceInfo(DeviceEntity deviceEntity,JSONObject realDevice,String sceneStr){
        //实例化设备信息
        RequestTokenVo requestTokenVo;
        requestTokenVo =getRealInfoVo(realDevice,deviceEntity,sceneStr);
        Request request2 = ConstructRequest.constructPost(requestTokenVo);
        return request2;
    }

    public RequestTokenVo getRealInfoVo(JSONObject realDevice,DeviceEntity deviceEntity,String sceneStr){
        RequestTokenVo requestTokenVo;

        Map realInfoMap = URLmakeTools.url_split(DirTable.getRealUrlInfo);
        String realInfoUrl = UrlBodyCreaterTool.getUrlFromEntityAndMap(DirTable.realUrlInfoHost,realInfoMap,deviceEntity);
        requestTokenVo = new RequestTokenVo();
        //生成请求头信息
        Map<String,String> realDeviceHeader = new HashMap<String,String>();

        realDeviceHeader.put("Accept-Encoding","gzip");
        realDeviceHeader.put("Cache-Control","max-stale=0");
        realDeviceHeader.put("Content-Type","application/x-www-form-urlencoded");
        realDeviceHeader.put("Host","i.snssdk.com");
        realDeviceHeader.put("Connection","Keep-Alive");
        realDeviceHeader.put("Cookie",deviceEntity.getCookie()+";"+"qh[360]=1");
        realDeviceHeader.put("User-Agent","okhttp/3.8.1");

        //生成body信息
        Map<String,String> realDeviceBody = new HashMap<String,String>();
        //生成加密的device_Info 信息的方法；
        //String device_Info = getRealDevice_Info(realDevice);
        String buff_Info = getRealDevice_Info(realDevice);
        realDeviceBody.put("device_info",buff_Info);
        realDeviceBody.put("device_type",deviceEntity.getDevice_type());
        realDeviceBody.put("iid",deviceEntity.getIid());
        realDeviceBody.put("uuid",deviceEntity.getUuid());
        realDeviceBody.put("openudid",deviceEntity.getOpenudid());
        realDeviceBody.put("device_id",deviceEntity.getDeviceId());
        realDeviceBody.put("device_brand",deviceEntity.getDevice_brand());
        realDeviceBody.put("_rticket", ParamCreater.get_Rticket());
        realDeviceBody.put("scene",sceneStr);
        Map<String,String> bodyMap = URLmakeTools.url_split(DirTable.realUrlInfoBody);
        realDeviceBody = URLmakeTools.url_split(UrlBodyCreaterTool.getBodyByMapAndMap(bodyMap,realDeviceBody));
        requestTokenVo.setUrl(realInfoUrl);
        requestTokenVo.setHeader(realDeviceHeader);
        requestTokenVo.setBody(realDeviceBody);
        return requestTokenVo;
    }

    public String getRealDevice_Info(JSONObject jsonObject){
        byte[] enCfb = ScretAES.encryptCodeWithCFB(jsonObject.toString());
        String sixCFB = ScretAES.bytesToHexFun(enCfb);
        String crcString = Crc32.encordToCrc(sixCFB);
        String sendInfo = sixCFB+","+crcString;
        byte[] gzipByte = GzipGetteer.compress(sendInfo);
        String allKey = ScretAES.bytesToHexFun(gzipByte);
        System.out.println(allKey);
        return allKey;
    }
}
