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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.ParamCreater;
import params.tools.RequestURLCreater;
import platform.email.EmailGetter;
import platform.main.TvRegisterMaker;
import platform.main.UserPowerGetter;
import po.PhonePo;
import po.RequestTokenVo;
import vpntool.VPNTools;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return "呵呵哒哒";
    }

    public boolean sendMessage(Request request, OkHttpClient okHttpClient){
        return false;
    }

    public boolean oneUserInfo(){
        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
        //tvRegisterMaker.registerUserToTv();
        DeviceEntity deviceEntity = tvRegisterMaker.registerUserToTv();//deviceRepository.getOne(5);
        //System.out.println(save.getCookie());
        OkHttpClient okHttpClient = tvRegisterMaker.okHttpClient;
        //获取设备真实信息。
        JSONObject realDevice = JsonTableGetter.contrustTest();
        Request requestUpload = sendRealDeviceInfo(deviceEntity,realDevice,"cold_start");
        Response response = null;
        String jsonString = null;
        JSONObject resultJson =null;
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
        //为了测试而添加的读取方法
        UrlRequestEntity urlRequestEntity= allUrl.get(0);
        UrlRequestEntity urlRequestEntity1 = allUrl.get(1);
        EmailGetter emailGetter = new EmailGetter();
        emailGetter.loginIT();
        PhonePo phonePo = emailGetter.getPhoneNumber();
        Request request = tvRegisterMaker.sendMessageForRegister(urlRequestEntity,deviceEntity,phonePo,"");
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
        Request request2 = UserPowerGetter.registerFriends(deviceEntity,dyUserEntity);
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
        request2 =UserPowerGetter.abTestParams(deviceEntity,dyUserEntity);
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
        deviceEntities.add(deviceEntity);
        userEntities.add(dyUserEntity);
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
        String realInfoUrl = TvRegisterMaker.getUrlForOdinTT(DirTable.realUrlInfoHost,realInfoMap,deviceEntity);
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
        realDeviceBody = URLmakeTools.url_split(TvRegisterMaker.getUrlByMapAndMap(bodyMap,realDeviceBody));
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
