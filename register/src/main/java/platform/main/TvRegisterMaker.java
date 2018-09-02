package platform.main;

import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import jsonreader.tools.GzipGetteer;
import jsonreader.tools.JsonTableGetter;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import platform.tv.DeviceTvRegister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @program: protool
 * @description: 某应用注册流程全部实现类
 * @author: Mr.gao
 * @create: 2018-09-02 16:10
 **/
public class TvRegisterMaker {

    DeviceTvRegister deviceTvRegister;
    OkHttpClient okHttpClient;

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
            System.out.println(jsonString);
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
            deviceEntity.setDevice_platform("Android");
            deviceEntity.setDevice_type("device_model");
            deviceEntity.setIid(resultJson.getString("install_id"));
            deviceEntity.setOpenudid(headerJson.getString("openudid"));
            deviceEntity.setUuid(headerJson.getString("udid"));
            ArrayList<String> strings = getCookieFromResponseHeaders(getStrCookie(header));
            StringBuilder cookies = new StringBuilder();
            for(int i=0;i<strings.size();i++){
                cookies.append(strings.get(i)+";");
            }
            deviceEntity.setCookie(cookies.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return deviceEntity;
    }

    public boolean sendMessageForRegister(UrlRequestEntity urlRequestEntity,DeviceEntity deviceEntity){

        String host = urlRequestEntity.getHost();
        String message = urlRequestEntity.getMessage();
        
        return true;
    }
    /**
     *
     * @param responseHeaders 提取headers里面的cookie信息
     * @return
     */
    private ArrayList<String> getStrCookie(Headers responseHeaders){

        int responseHeadersLength = responseHeaders.size();
        ArrayList<String> cookie = new ArrayList<String>();
        for (int i = 0; i < responseHeadersLength; i++){
            String headerName = responseHeaders.name(i);
            String headerValue = responseHeaders.value(i);
            if(headerName.equals("Set-Cookie")){
                cookie.add(headerValue);
            }
        }
        System.out.println(cookie.toString());
        return cookie;
    }

    /**
     *
     * @param responseHeaders 包含所有cookie的链表，用于获取有用的cookie
     * @return 只包含重要信息的cookie字符串
     */
    private static ArrayList<String> getCookieFromResponseHeaders(ArrayList<String> responseHeaders){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0;i < responseHeaders.size();i++){
            String []temp = responseHeaders.get(i).split(";");
            result.add(temp[0]);
        }
        return result;
    }

    public static void main(String[]args){
        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
        System.out.println(tvRegisterMaker.registerUserToTv().toString());
    }
}
