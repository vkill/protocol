package platform.main;

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
import org.springframework.web.servlet.tags.Param;
import params.ParamCreater;
import params.tools.KeyControler;
import params.tools.RequestURLCreater;
import platform.tv.DeviceTvRegister;
import po.PhonePo;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: protool
 * @description: 某应用注册流程全部实现类
 * @author: Mr.gao
 * @create: 2018-09-02 16:10
 **/
public class TvRegisterMaker {

    DeviceTvRegister deviceTvRegister;
    public OkHttpClient okHttpClient;

    public TvRegisterMaker(){
        deviceTvRegister = new DeviceTvRegister();
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
                .build();
    }

    public DeviceEntity registerUserToTv(){
        //DeviceTvRegister deviceRegisterTV = new DeviceTvRegister();
        JSONObject jsonObject = JsonTableGetter.construtJson();
        Request request = deviceTvRegister.getDeviceCreaterRequest(jsonObject);
        Response response = null;
        String jsonString = null;
        Headers header = null;
        JSONObject resultJson =null;
        try {
            response = okHttpClient.newCall(request).execute();
            jsonString =GzipGetteer.uncompressToString(response.body().bytes());
            header = response.headers();
            resultJson = new JSONObject(jsonString);
            //System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("注册设备结果获取失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册设备结果获取失败");
        }
        //处理注册设备返回的信息
        DeviceEntity deviceEntity = new DeviceEntity();
        try {
            JSONObject headerJson = jsonObject.getJSONObject("header");
            deviceEntity.setDeviceId(resultJson.getString("device_id"));
            deviceEntity.setDevice_brand(headerJson.getString("device_brand"));
            deviceEntity.setDevice_platform("android");
            deviceEntity.setDevice_type(headerJson.getString("device_model"));
            deviceEntity.setIid(resultJson.getString("install_id"));
            deviceEntity.setOpenudid(headerJson.getString("openudid"));
            deviceEntity.setUuid(headerJson.getString("udid"));
            ArrayList<String> strings = RequestURLCreater.getCookieFromResponseHeaders(RequestURLCreater.getStrCookie(header));
            StringBuilder cookies = new StringBuilder();
            for(int i=0;i<strings.size();i++){
                if(i==strings.size()-1){
                    cookies.append(strings.get(i));
                    break;
                }
                cookies.append(strings.get(i)+";");
            }
            deviceEntity.setCookie(cookies.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //获取odin_tt
        Map map = URLmakeTools.url_split(DirTable.getOdinTT);
        String url = getUrlForOdinTT(DirTable.OdinTTUrlHost,map,deviceEntity);
        RequestTokenVo requestTokenVo = new RequestTokenVo();
        requestTokenVo.setUrl(url);

        Map<String,String> odin_header = getAllGoodHeaders(deviceEntity.getCookie());
        requestTokenVo.setHeader(odin_header);
        Request request1 = ConstructRequest.constructGet(requestTokenVo);
        try {
            response = okHttpClient.newCall(request1).execute();
            jsonString =GzipGetteer.uncompressToString(response.body().bytes());
            header = response.headers();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("注册设备odinTT失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册设备odinTT失败");
        }
        ArrayList<String> strings = RequestURLCreater.getCookieFromResponseHeaders(RequestURLCreater.getStrCookie(header));
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
        return deviceEntity;
    }


    public static String getUrlForOdinTT(String hostAndMsg,Map<String,String> allVauleMaps,DeviceEntity deviceEntity){
        Map<String,String> deviceMap = deviceEntity.getDeviceMap();
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append(hostAndMsg);
        stringBuilder.append(getUrlByMapAndMap(allVauleMaps,deviceMap));
        stringBuilder.append(KeyControler.getKeyForUse());
        return stringBuilder.toString();
    }

    public static String getUrlByMapAndMap(Map<String,String> allVauleMaps,Map<String,String> changeVaule){
        StringBuilder stringBuilder = new StringBuilder() ;
        int i = 0;
        for(String key :allVauleMaps.keySet()){
            if(i==0){
                if(changeVaule.containsKey(key)){
                    stringBuilder.append(key+"="+changeVaule.get(key));
                }else{
                    stringBuilder.append(key+"="+allVauleMaps.get(key));
                }

            }
            else{
                if(changeVaule.containsKey(key)){
                    stringBuilder.append("&"+key+"="+changeVaule.get(key));
                }else{
                    stringBuilder.append("&"+key+"="+allVauleMaps.get(key));
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }
    public Request sendMessageForRegister(UrlRequestEntity urlRequestEntity,DeviceEntity deviceEntity,PhonePo phonePo,String code){

        String host = urlRequestEntity.getHost();
        String message = urlRequestEntity.getMessage();
        Map<String,String> deviceMap = deviceEntity.getDeviceMap();
        String times = ParamCreater.get_Rticket();
        deviceMap.put("_rticket",times);
        deviceMap.put("ts",ParamCreater.get_Ts(times));
        String postUrl = RequestURLCreater.getSendMessageFromMap(host,message,deviceMap);
        postUrl+= KeyControler.getKeyForUse();
        //System.out.println("请求发送短信url"+postUrl);
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Accept-Encoding","gzip");
        headers.put("Cache-Control","max-stale=0");
        headers.put("Content-Type","application/x-www-form-urlencoded");
        headers.put("Host","is.snssdk.com");
        headers.put("Connection","Keep-Alive");
        headers.put("User-Agent","okhttp/3.8.1");
        String cookies = deviceEntity.getCookie()+"qh[360]=1;";
        //System.out.println("cookie 内容"+ cookies);
        headers.put("Cookie",cookies);
        RequestTokenVo requestTokenVo = new RequestTokenVo();
        requestTokenVo.setUrl(postUrl);
        //System.out.println("获取的电话号码"+phonePo.getPhone_Num());
        Map<String,String> body = new HashMap<String,String>();
        if(code.equals("")){
            deviceMap =getDevice(deviceMap,phonePo);
            body = RequestURLCreater.getBodyForMessage(deviceMap);

            System.out.println("body内容:  "+MapToString(body));
        }
        else{
            deviceMap = getDeviceCode(deviceMap,phonePo,code);
            body = RequestURLCreater.getBodyForRegister(deviceMap);
        }

        requestTokenVo.setHeader(headers);
        requestTokenVo.setBody(body);
        System.out.println(requestTokenVo.getBody());
        return ConstructRequest.constructPost(requestTokenVo);
    }
    public static String MapToString(Map map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "&" : "");
        }
        return sb.toString();
    }

    public Map<String,String> getDevice(Map<String,String> kao,PhonePo phonePo){
        String phone = "+66"+phonePo.getPhone_Num();
        kao.put("mobile",ParamCreater.change_Mobile_to_Src(phone));
        return kao;
    }

    public Map<String,String> getDeviceCode(Map<String,String> kao,PhonePo phonePo,String code){
        String phone = "+66"+phonePo.getPhone_Num();
        kao.put("mobile",ParamCreater.change_Mobile_to_Src(phone));
        kao.put("password",ParamCreater.change_Mobile_to_Src("asd123456"));
        kao.put("code",ParamCreater.change_Mobile_to_Src(code));
        return kao;
    }

    public static Map<String, String> getAllGoodHeaders(String cookies){
        Map<String,String> odin_header = new HashMap<String,String>();
        odin_header.put("Accept-Encoding","gzip");
        odin_header.put("Cache-Control","max-stale=0");
        odin_header.put("Host","is.snssdk.com");
        odin_header.put("Connection","Keep-Alive");
        odin_header.put("Cookie",cookies);
        odin_header.put("User-Agent","okhttp/3.8.1");
        return odin_header;
    }

    public static void main(String[]args){
//        DeviceEntity deviceEntity = new DeviceEntity();
//        deviceEntity.setUuid("865166026228901");
//        deviceEntity.setOpenudid("fa0c47b8259279fb");
//        deviceEntity.setDevice_type("vivo x5m");
//        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
//        System.out.println(tvRegisterMaker.getRealDevice_Info(JsonTableGetter.contrustJsonForReal(deviceEntity)));

    }
}
