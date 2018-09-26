package platform.email;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import po.HostIPPo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: protool
 * @description: 动态ip获取实现类
 * @author: Mr.gao
 * @create: 2018-09-19 13:57
 **/
public class HostIPGetter {

    public static String spiderId = "8d58e76da82e431cb6021e32d1b875c3";
    public static String orderno = "YZ20189222135SFVprZ";
    public static int returnType = 2;
    public static int count = 10;
    //http://api.xdaili.cn/xdaili-api//greatRecharge/getGreatIp?spiderId=8d58e76da82e431cb6021e32d1b875c3&orderno=YZ20189222135SFVprZ&returnType=2&count=10
    //包含上面参数的URL                   http://api.xdaili.cn/xdaili-api//greatRecharge/getGreatIp?spiderId=8d58e76da82e431cb6021e32d1b875c3&orderno=YZ20189222135SFVprZ&returnType=2&count=5
    public static String ipHostGetter = "http://api.xdaili.cn/xdaili-api//greatRecharge/getGreatIp?spiderId="+spiderId+"&orderno="+orderno+"&returnType="+returnType+"&count=";
    public static String endStr ="10032";
    public static String errorStr ="10036";
    public static String successStr = "0";
    public static ArrayList<HostIPPo> getIpByXdali(){
        String ipHostGetterAll = ipHostGetter+count;
        String tag =errorStr;
        Document document = null;
        HostIPPo hostIPPo = null;
        JSONArray jsonArray  = null;
        ArrayList<HostIPPo> hostIPPos = new ArrayList<>();
        try {
            document = Jsoup.connect(ipHostGetterAll).get();
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
                JSONObject jsonObject1 = null;
                for(int i=0;i<jsonArray.length();i++){
                    jsonObject1 = jsonArray.getJSONObject(i);
                    String hoster = jsonObject1.getString("ip");
                    int port = jsonObject1.getInt("port");
                    hostIPPo = new HostIPPo(hoster,port);
                    hostIPPos.add(hostIPPo);
                }
                return hostIPPos;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(tag.equals(endStr)){
            System.out.println("可以关了啦");
            return null;
        }
        else{
            return hostIPPos;
        }
        return hostIPPos;
    }

}
