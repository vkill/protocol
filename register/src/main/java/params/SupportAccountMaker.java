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
 * @description: 获取预加载视频id的构造模块
 * @author: Mr.Jia
 * @create: 2018-09-05 20:41
 **/
public class SupportAccountMaker {

    public static ArrayList<String> getAwemeListMaker(DeviceEntity deviceEntity, UrlRequestEntity urlRequestEntity){

        //获取设备信息
        String deviceID = deviceEntity.getDeviceId();
        String deviceBrand = deviceEntity.getDevice_brand();
        String devicePlatform = deviceEntity.getDevice_platform();
        String deviceType = deviceEntity.getDevice_type();
        String iid = deviceEntity.getIid();
        String openudid = deviceEntity.getOpenudid();
        String uuid = deviceEntity.getUuid();
        String cookie = deviceEntity.getCookie();


        //获取并构建url信息，包括host、msg、token
        String host = urlRequestEntity.getHost();
        String msg = urlRequestEntity.getMessage();

        //构造token
        Map<String, String> token = new HashMap<String, String>();

        token.put("type","0");
        token.put("max_cursor","0");
        token.put("min_cursor","0");
        token.put("count","6");
        token.put("volume","0.7333333333333333");
        token.put("pull_type","0");
        token.put("app_type","normal");
        token.put("os_api","22");
        token.put("device_type",deviceType);
        token.put("device_platform",devicePlatform);
        token.put("ssmix","a");
        token.put("iid",iid);
        token.put("manifest_version_code","176");
        token.put("dpi","240");
        token.put("uuid",uuid);
        token.put("version_code","176");
        token.put("app_name","aweme");
        token.put("version_name","1.7.6");
        token.put("openudid",openudid);
        token.put("device_id",deviceID);
        token.put("resolution","1280*720");
        token.put("os_version","5.1.1");
        token.put("language","zh");
        token.put("device_brand",deviceBrand);
        token.put("ac","wifi");
        token.put("update_version_code","1762");
        token.put("aid","1128");
        token.put("channel","tengxun");
        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);
        token.put("_rticket",_rticket);
        token.put("ts",ts);
        token.put("as","a1iosdfgh");
        token.put("cp","androide1");
        String url = ConstructRequestUrl.constructUrl(host, msg, token);


        //下面开始构造header中的cookie
        String []line_split = cookie.split(";");
        Map <String, String> stack = new HashMap<String, String>();
        for(int i = 0;i < line_split.length;i++){
            String []stack_split =line_split[i].split("=");
            stack.put(stack_split[0],stack_split[1]);
        }

        Map<String, String> headerMap = urlRequestEntity.getHeaderMap();
        Map<String, String> header_cookie = new HashMap<String, String>();
        Map<String, String> data = new HashMap<String, String>();
        String []cookie_data = cookie.split(";");

        for(int i = 0;i < cookie_data.length;i++){
            String []data_temp = cookie_data[i].split("=");
            data.put(data_temp[0],data_temp[1]);
        }

        String cookie_map = headerMap.get("Cookie");
        cookie_map = cookie_map.split("[{]")[1].split("[}]")[0];
        String []cookie_map_list = cookie_map.split(";");
        for(int i = 0;i < cookie_map_list.length;i++){
            String []cookie_list_temp = cookie_map_list[i].split("=");
            header_cookie.put(cookie_list_temp[0], data.get(cookie_list_temp[0]));
        }
        header_cookie.put("qh[360]","1");
        Map<String, String> header = new HashMap<String, String>();


        String header_str = MapToString(header_cookie);


        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
        header.put("Accept-Encoding","gzip");
        header.put("Cookie",header_str);
        header.put("User-Agent","okhttp/3.8.1");


        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(null);
        Request request = null;
        request = ConstructRequest.constructGet(requestToSend);

        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        ArrayList<String> resultToReturn = new ArrayList<String>();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                System.out.println("响应成功");
                String result = GzipGetteer.uncompressToString(response.body().bytes());

                String []temp = result.split("\"aweme_id\": ");
                ArrayList<String> id_list = new ArrayList<String>();
                for(int i = 1;i < temp.length;i ++){
                    String []temp1 = temp[i].split(",");
                    char []array = temp1[0].toCharArray();
                    String str = "";
                    for(int j = 1;j < array.length - 1;j++){
                        str += array[j];
                    }
                    id_list.add(str);
                }

                for(int i = 0;i < id_list.size();i +=3){
                    resultToReturn.add(id_list.get(i));

                }

            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");

            }
        });

        return resultToReturn;
    }

    public static void getVideoMaker(String awemeId, DeviceEntity deviceEntity, UrlRequestEntity urlRequestEntity){


        String cookie = deviceEntity.getCookie();

        //获取并构建url信息，包括host、msg、token
        //UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);
        String host = urlRequestEntity.getHost();
        String msg = urlRequestEntity.getMessage();

        //构造token
        Map<String, String> token = new HashMap<String, String>();
        token.put("aweme_id",awemeId);

        //type为1则代表点赞，0为取消赞
        token.put("os_api","22");
        token.put("device_type",deviceEntity.getDevice_type());
        token.put("device_platform",deviceEntity.getDevice_platform());
        token.put("ssmix","a");
        token.put("iid",deviceEntity.getIid());
        token.put("manifest_version_code","176");
        token.put("dpi","240");
        token.put("uuid",deviceEntity.getUuid());
        token.put("version_code","176");
        token.put("openudid",deviceEntity.getOpenudid());
        token.put("device_id",deviceEntity.getDeviceId());
        token.put("resolution","720*1280");
        token.put("os_version","5.1.1");
        token.put("language","zh");
        token.put("device_brand",deviceEntity.getDevice_brand());
        token.put("ac","wifi");
        token.put("update_version_code","1762");
        token.put("aid","1128");
        token.put("channel","tengxun");
        token.put("version_name","1.7.6");
        token.put("app_name","aweme");
        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);
        token.put("_rticket",_rticket);
        token.put("ts",ts);
        token.put("as","a1iosdfgh");
        token.put("cp","androide1");

        //url构建完成,其中cookie需要从前面的cookie参数中提取
        String url = ConstructRequestUrl.constructUrl(host, msg, token);

        String []line_split = cookie.split(";");
        Map <String, String> stack = new HashMap<String, String>();
        for(int i = 0;i < line_split.length;i++){
            String []stack_split =line_split[i].split("=");
            stack.put(stack_split[0],stack_split[1]);
        }

        Map<String, String> headerMap = urlRequestEntity.getHeaderMap();
        Map<String, String> header_cookie = new HashMap<String, String>();
        Map<String, String> data = new HashMap<String, String>();
        String []cookie_data = cookie.split(";");

        for(int i = 0;i < cookie_data.length;i++){
            String []data_temp = cookie_data[i].split("=");
            data.put(data_temp[0],data_temp[1]);
        }

        String cookie_map = headerMap.get("Cookie");
        cookie_map = cookie_map.split("[{]")[1].split("[}]")[0];
        String []cookie_map_list = cookie_map.split(";");
        for(int i = 0;i < cookie_map_list.length;i++){
            String []cookie_list_temp = cookie_map_list[i].split("=");
            header_cookie.put(cookie_list_temp[0], data.get(cookie_list_temp[0]));
        }
        header_cookie.put("qh[360]","1");
        Map<String, String> header = new HashMap<String, String>();

        String header_str = MapToString(header_cookie);

        header.put("Accept-Encoding","gzip");
        header.put("Cache-Control","max-stale=0");
        header.put("Content-Type","Content-Type");
        header.put("Content-Length","500");
        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
        header.put("Cookie",header_str);
        header.put("User-Agent","okhttp/3.8.1");


        //构造body
        Map <String, String> body = new HashMap<String, String>();

        body.put("item_id",awemeId);
        body.put("tab_type","0");
        body.put("play_delta","1");
        body.put("aweme_type","0");
        body.put("retry_type","no_retry");
        body.put("os_api","22");
        body.put("device_type",deviceEntity.getDevice_type());
        body.put("device_platform",deviceEntity.getDevice_platform());
        body.put("ssmix","a");
        body.put("iid",deviceEntity.getIid());
        body.put("manifest_version_code","176");
        body.put("dpi","240");
        body.put("uuid",deviceEntity.getUuid());
        body.put("version_code","176");
        body.put("app_name","aweme");
        body.put("version_name","1.7.6");
        body.put("openudid",deviceEntity.getOpenudid());
        body.put("device_id",deviceEntity.getDeviceId());
        body.put("resolution","1280*720");
        body.put("os_version","5.1.1");
        body.put("language","zh");
        body.put("device_brand",deviceEntity.getDevice_brand());
        body.put("ac","wifi");
        body.put("update_version_code","1762");
        body.put("aid","1128");
        body.put("channel","tengxun");
        body.put("_rticket",_rticket);


        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(body);
        Request request = null;
        request = ConstructRequest.constructPost(requestToSend);

        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                System.out.println("响应成功");
                System.out.println(GzipGetteer.uncompressToString(response.body().bytes() ,"utf-8"));
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                System.out.println("响应失败");
            }
        });

    }

    public static String getAwemeId(DeviceEntity deviceEntity, DYUserEntity dyUserEntity, String user_id){

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);

        String url = "https://aweme.snssdk.com/aweme/v1/aweme/post/?user_id="+user_id+"&max_cursor=0&count=20&retry_type=no_retry&iid="+deviceEntity.getIid()+"&device_id="+deviceEntity.getDeviceId()+"&ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type="+deviceEntity.getDevice_type()+"&device_brand="+deviceEntity.getDevice_brand()+"&language=zh&os_api=25&os_version=7.1.2&uuid="+deviceEntity.getUuid()+"&openudid="+deviceEntity.getOpenudid()+"&manifest_version_code=176&resolution=1280*720&dpi=320&update_version_code=1762&_rticket="+_rticket+"&ts="+ts+"&as=a1iosdfgh&cp=androide1";

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host","aweme.snssdk.com");
        header.put("Cache-Control","max-stale=60");
        header.put("Connection","Keep-Alive");
        header.put("User-Agent","okhttp/3.8.1");
        header.put("Cookie",dyUserEntity.getUserCookie());


        RequestTokenVo requestToSend = new RequestTokenVo();
        requestToSend.setUrl(url);
        requestToSend.setHeader(header);
        requestToSend.setBody(null);
        Request request = null;
        request = ConstructRequest.constructGet(requestToSend);

        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        String result_awemeId = "";
        try {
            Response response = call.execute();
            String result = GzipGetteer.uncompressToString(response.body().bytes(),"utf-8");
            String []temp_result = result.split(",");
            for(int i = 0;i < temp_result.length;i++){

                String []line_split = temp_result[i].split("\"aweme_id\":");
                if(line_split.length == 2){
                    char []array = line_split[1].toCharArray();
                    String awemeId = "";
                    for(int j = 1;j < array.length - 2;j++){
                        awemeId += array[j];
                    }
                    result_awemeId = awemeId;
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result_awemeId;
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
