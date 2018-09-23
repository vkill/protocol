package params;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import params.tools.ConstructRequestUrl;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: register
 * @description: 构造关注模块
 * @author: Mr.Jia
 * @create: 2018-09-04 15:15
 **/
public class FollowMaker {

    public static ArrayList<String> FollowMaker(String user_id, DYUserEntity dyUserEntity, DeviceEntity deviceEntity) {


        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);

        String url = "https://aweme.snssdk.com/aweme/v1/commit/follow/user/?user_id="+user_id+"&type=1&retry_type=no_retry&iid="+deviceEntity.getIid()+"&device_id="+deviceEntity.getDeviceId()+"&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type="+deviceEntity.getDevice_type()+"&device_brand="+deviceEntity.getDevice_brand()+"&language=zh&os_api=22&os_version=7.1.2&uuid="+deviceEntity.getUuid()+"&openudid="+deviceEntity.getOpenudid()+"&manifest_version_code=176&resolution=1280*720&dpi=320&update_version_code=1762&_rticket="+_rticket+"&ts="+ts+"&as=a1iosdfgh&cp=androide1";


        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Cache-Control","max-stale=0");
        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
        header.put("Cookie",dyUserEntity.getUserCookie()+";qh[360]=1;install_id=" + deviceEntity.getIid());
        header.put("User-Agent","okhttp/3.8.1");

        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(null);
        Request request = null;
        request = ConstructRequest.constructGet(requestToSend);

        ArrayList <String> resultToReturn = new ArrayList<>();
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                String result = GzipGetteer.uncompressToString(response.body().bytes());
                //System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
                System.out.println(result);
                resultToReturn.add(result);
                resultToReturn.add(followTest(user_id, dyUserEntity, deviceEntity));

            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });
        return resultToReturn;
    }

    public static String followTest(String user_id, DYUserEntity dyUserEntity, DeviceEntity deviceEntity){

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        String max_time = String.valueOf(temp_ts);
        ts = String.valueOf(temp_ts);


        String url = "https://api.amemv.com/aweme/v1/user/following/list/?user_id="+dyUserEntity.getUid()+"&max_time="+max_time+"&count=20&retry_type=no_retry&iid="+deviceEntity.getIid()+"&device_id="+deviceEntity.getDeviceId()+"&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type="+deviceEntity.getDevice_type()+"&device_brand="+deviceEntity.getDevice_brand()+"&language=zh&os_api=25&os_version=7.1.2&uuid="+deviceEntity.getUuid()+"&openudid="+deviceEntity.getOpenudid()+"&manifest_version_code=176&resolution=1280*720&dpi=320&update_version_code=1762&_rticket="+_rticket+"&ts="+ts+"&as=a1iosdfgh&cp=androide1";

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Cache-Control","max-stale=0");
        header.put("Host","api.amemv.com");
        header.put("Connection","Keep-Alive");
        header.put("Cookie",dyUserEntity.getUserCookie()+";qh[360]=1;install_id=" + deviceEntity.getIid());
        header.put("User-Agent","okhttp/3.8.1");

        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(null);
        Request request = null;
        request = ConstructRequest.constructGet(requestToSend);

        ArrayList<String> resultToReturn = new ArrayList<>();
        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                try {
                    Thread.sleep(100);
                    String responseLine = GzipGetteer.uncompressToString(response.body().bytes());
                    System.out.println(responseLine);
                    String []temp = responseLine.split(user_id);
                    if(temp.length == 0){
                        Thread.sleep(100);
                    }
                    if(temp.length >=2){
                        System.out.println("关注成功");
                        resultToReturn.add("success");
                    }else{
                        System.out.println("关注失败");
                        resultToReturn.add("failure");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });

        return resultToReturn.get(0);
    }

    public static String MapToString(Map map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? ";" : "");
        }
        return sb.toString();
    }
}
