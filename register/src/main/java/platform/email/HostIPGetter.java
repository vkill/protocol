package platform.email;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import po.HostIPPo;

import java.io.IOException;

/**
 * @program: protool
 * @description: 动态ip获取实现类
 * @author: Mr.gao
 * @create: 2018-09-19 13:57
 **/
public class HostIPGetter {

    public static String ipHostGetter = "http://api.xdaili.cn/xdaili-api//greatRecharge/getGreatIp?spiderId=8d58e76da82e431cb6021e32d1b875c3&orderno=YZ20189193312tH3ZAE&returnType=2&count=1";
    public static String endStr ="10032";
    public static String errorStr ="10036";
    public static String successStr = "0";
    public static HostIPPo getIpByXdali(){
        String tag =errorStr;
        Document document = null;
        HostIPPo hostIPPo = null;
        JSONArray jsonArray  = null;
        try {
            document = Jsoup.connect(ipHostGetter).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(document.body().text());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            tag = jsonObject.getString("ERRORCODE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(tag.equals("0")){
            try {
                jsonArray = jsonObject.getJSONArray("RESULT");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                String hoster = jsonObject1.getString("ip");
                int port = jsonObject1.getInt("port");
                hostIPPo = new HostIPPo(hoster,port);
                return hostIPPo;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(tag.equals(endStr)){
            return new HostIPPo("-1",-1);
        }
        else{
            return new HostIPPo("0",0);
        }
        return new HostIPPo("-2",-2);
    }

}
