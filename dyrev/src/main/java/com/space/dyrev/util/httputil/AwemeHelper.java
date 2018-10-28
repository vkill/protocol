package com.space.dyrev.util.httputil;

import com.space.dyrev.util.formatutil.GzipGetteer;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 *        @Date: 2018/10/28 21:43
 *        @Description: 
 **/
public class AwemeHelper {
    public static String share(String aweme_id){

        String _rticket = String.valueOf(System.currentTimeMillis());
        char []temp = _rticket.toCharArray();
        String ts = "";
        for(int i = 0;i < temp.length - 3;i++){
            ts += temp[i];
        }
        long temp_ts = Long.parseLong(ts);
        temp_ts ++;
        ts = String.valueOf(temp_ts);

        String url = "https://api.amemv.com/aweme/v1/aweme/detail/?aweme_id="+aweme_id+"&retry_type=no_retry&ac=wifi&channel=aweGW&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type=Redmi+4X&device_brand=Xiaomi&language=zh&os_api=25&os_version=7.1.2&manifest_version_code=176&resolution=720*1280&dpi=320&update_version_code=1762&_rticket="+_rticket+"&ts="+ts+"&as=a1iosdfgh&cp=androide1";

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept-Encoding","gzip");
        header.put("Host","api.amemv.com");
        header.put("Connection","Keep-Alive");
        //header.put("Cookie","install_id="+iid+";qh[360]=1;odin_tt="+odin_tt);
        header.put("User-Agent","okhttp/3.8.1");
        header.put("sdk-version","1");
        header.put("X-SS-TC","0");
        //header.put("User-Agent","com.ss.android.ugc.aweme/270 (Linux; U; Android 7.1.2; zh_CN; Redmi 4X; Build/N2G47H; Cronet/58.0.2991.0)");

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        for(String key : header.keySet()){
            builder.addHeader(key, header.get(key));
        }
        Request request = builder.get().build();

        OkHttpClient okHttpClient=new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        String result = "";
        try {
            Response response = call.execute();
            String resultLine = GzipGetteer.uncompressToString(response.body().bytes());
//            System.out.println(resultLine);
            String []temp1 = resultLine.split(", ");
            for(int i = 0;i < temp1.length;i++){
                if(temp1[i].split(":")[0].equals("\"author_user_id\"")){
                    result = temp1[i].split(":")[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] array = result.toCharArray();
        String resultToReturn = "";
        for(int i = 0;i < array.length;i++){
            if (Character.isDigit(array[i])){
                resultToReturn += array[i];
            }
        }
        return resultToReturn;
    }
}
