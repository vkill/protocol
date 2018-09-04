package params;

import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import params.tools.ConstructRequestUrl;
import po.RequestTokenVo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: register
 * @description: 构造点赞模块
 * @author: Mr.Jia
 * @create: 2018-09-04 11:39
 **/
public class ThumbsUpMaker {
    public static String thumbsUpMaker(String aweme_id, DeviceEntity deviceEntity, UrlRequestEntity urlRequestEntity) {


        //获取设备信息
        //DeviceEntity deviceEntity = deviceService.getDeviceMsg(1);
        String deviceID = deviceEntity.getDeviceId();
        String deviceBrand = deviceEntity.getDevice_brand();
        String devicePlatform = deviceEntity.getDevice_platform();
        String deviceType = deviceEntity.getDevice_type();
        String iid = deviceEntity.getIid();
        String openudid = deviceEntity.getOpenudid();
        String uuid = deviceEntity.getUuid();
        String cookie = deviceEntity.getCookie();


        //获取并构建url信息，包括host、msg、token
        //UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);
        String host = urlRequestEntity.getHost();
        String msg = urlRequestEntity.getMessage();

        //构造token
        Map<String, String> token = new HashMap<String, String>();
        token.put("aweme_id",aweme_id);

        //type为1则代表点赞，0为取消赞
        token.put("type","1");
        token.put("retry_type","no_retry");
        token.put("iid",iid);
        token.put("device_id",deviceID);
        token.put("ac","wifi");
        token.put("channel","tengxun");
        token.put("aid","1128");
        token.put("app_name","aweme");
        token.put("version_code","176");
        token.put("version_name","1.7.6");
        token.put("device_platform",devicePlatform);
        token.put("ssmix","a");
        token.put("device_type",deviceType);
        token.put("device_brand",deviceBrand);
        token.put("language","zh");
        token.put("os_api","22");
        token.put("os_version","5.1.1");
        token.put("uuid",uuid);
        token.put("openudid",openudid);
        token.put("manifest_version_code","176");
        token.put("resolution","1280*720");
        token.put("dpi","240");
        token.put("update_version_code","1762");
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
        header.put("Host","aweme.snssdk.com");
        header.put("Connection","Keep-Alive");
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
        return null;
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
