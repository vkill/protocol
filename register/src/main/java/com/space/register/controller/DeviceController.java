package com.space.register.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;
import jsonreader.tools.GzipGetteer;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.tools.RequestURLCreater;
import platform.email.EmailGetter;
import platform.main.TvRegisterMaker;
import po.PhonePo;
import vpntool.VPNTools;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    @Resource
    DeviceRepository deviceRepository;

    @Resource
    UrlRequestRepository urlRequestRepository;

    @Resource
    DYUserRepository dyUserRepository;
    public List<UrlRequestEntity> allUrl;
    public ArrayList<DeviceEntity> deviceEntities = new ArrayList<DeviceEntity>();
    public ArrayList<DYUserEntity> userEntities = new ArrayList<DYUserEntity>();

    @RequestMapping("/maker")
    public String deviceMain() {
        allUrl = urlRequestRepository.findAll();
        int worry=0;
        for(int i=0;i<1;i++){
            try{
                oneUserInfo();
            }catch (Exception e){
                System.out.println("死了一个");
                worry++;
                continue;
            }

            DeviceEntity deviceEntity;
            deviceEntity = deviceEntities.get(deviceEntities.size()-1);
            deviceEntity = deviceRepository.save(deviceEntity);
            DYUserEntity dyUserEntity = userEntities.get(userEntities.size()-1);
            dyUserEntity.setSimulationID(deviceEntity.getId()+"");
            dyUserRepository.save(dyUserEntity);
        }
        System.out.println("错误数量为:"+worry);
//        allUrl = urlRequestRepository.findAll();
//        int num = 20;
//        int now = deviceEntities.size();
//        String[] vpnName ={"vpn46","vpn47","vpn48","vpn49","vpn50"};
//        for(int i=0;i<num;i++){
//            if(VPNTools.connectVPN(vpnName[i%5],vpnName[i%5],"123")){
//                deviceBase = new DeviceBaseImpl();
//                userBase = new UserBaseImpl();
//                if(!oneUserInfo()){
//                    return "kao";
//                }
//                DeviceEntity deviceEntity;
//                deviceEntity = deviceEntities.get(now);
//                int sim_ID = deviceBase.insertOneInfo(deviceEntity);
//                DYUserEntity dyUserEntity = userEntities.get(now);
//                dyUserEntity.setSimulationID(sim_ID+"");
//                userBase.insertOneInfo(dyUserEntity);
//               // oneUserInfo();
//            }else{
//                if(VPNTools.disConnectVPN(vpnName[i%5])){
//                    System.out.println("断开连接："+vpnName[i%5]);
//                }
//                continue;
//            }
////
//
//            if(VPNTools.disConnectVPN(vpnName[i%5])){
//                System.out.println("断开连接："+vpnName[i%5]);
//            }
//        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "呵呵哒哒";
    }

    public boolean sendMessage(Request request, OkHttpClient okHttpClient){
        return false;
    }

    public boolean oneUserInfo(){
        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
        DeviceEntity deviceEntity = tvRegisterMaker.registerUserToTv();
        //System.out.println(save.getCookie());
        OkHttpClient okHttpClient = tvRegisterMaker.okHttpClient;

        //为了测试而添加的读取方法
        UrlRequestEntity urlRequestEntity= allUrl.get(0);
        UrlRequestEntity urlRequestEntity1 = allUrl.get(1);
        EmailGetter emailGetter = new EmailGetter();
        emailGetter.loginIT();
        PhonePo phonePo = emailGetter.getPhoneNumber();
        Request request = tvRegisterMaker.sendMessageForRegister(urlRequestEntity,deviceEntity,phonePo,"");
        Response response = null;
        String jsonString = null;
        JSONObject resultJson =null;
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
        String code = emailGetter.getIdentCode(phonePo.getP_ID());
        if(code.equals("请求超时")){
            return false;
        }
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
        ArrayList<String> strings = RequestURLCreater.getCookieFromResponseHeaders(RequestURLCreater.getStrCookie(headers));
        StringBuilder cookies = new StringBuilder();
        for(int i=0;i<strings.size();i++){
            if(i==strings.size()-1){
                cookies.append(strings.get(i));
                break;
            }
            cookies.append(strings.get(i)+";");
        }
        dyUserEntity.setUserCookie(cookies.toString());
        deviceEntities.add(deviceEntity);
        userEntities.add(dyUserEntity);
        return  true;
    }
}
