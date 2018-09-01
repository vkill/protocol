package params;

import enums.DeviceRegisterInfo;
import enums.paramtable.DirTable;
import jsonreader.tools.JsonTableGetter;
import okhttp3.FormBody;
import okhttp3.Request;
import org.json.JSONObject;
import po.DouyinAccountPo;

import java.io.IOException;
import java.util.Map;

/**
 * @ Author     ：GXDTJJC
 * @ Date       ：Created in 18:20 2018/8/30/030
 * @ Description  模拟生成模拟器请求方法类
 * @ Modified By：
 * @Version: $version$
 */
public class DeviceCreater {
    private static final String REQUEST_DEVICES_HOST = "https://ib.snssdk.com";

    private static final String DEVICE_REGISTER_API = "/service/2/device_register/?";

    private static final String COMMON_PARAMS = "ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a" +
            "&language=zh&os_version=5.1.1&manifest_version_code=176&update_version_code=1762&tt_data=a";

    public static String getUrlFromJsonAndMap(JSONObject jsonObject){
        Map <String,String> jsonMap = JsonTableGetter.construtMap(jsonObject);
        StringBuilder stringBuilder = new StringBuilder(REQUEST_DEVICES_HOST+DEVICE_REGISTER_API);
        for(DeviceRegisterInfo deviceRegisterInfo:DeviceRegisterInfo.values()){

            String key_Str = "\""+deviceRegisterInfo.getVaule()+"\"";
            //System.out.println(jsonMap.get(key_Str));
            if(deviceRegisterInfo.equals(DeviceRegisterInfo.ac)){
                if(jsonMap.containsKey(key_Str)){
                    stringBuilder.append(key_Str+"="+jsonMap.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                }
                else{
                    stringBuilder.append(key_Str+"="+DirTable.base_DeviceRegister_Info.get(deviceRegisterInfo));
                }
            }else {
                if(jsonMap.containsKey(key_Str)){
                    stringBuilder.append("&"+key_Str+"="+jsonMap.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                }
                else{
                    stringBuilder.append("&"+key_Str+"="+DirTable.base_DeviceRegister_Info.get(deviceRegisterInfo));
                }
            }
        }
        return stringBuilder.toString();
    }


    public static String getDeviceBaseURL(DouyinAccountPo douyinAccount) {
        String Url = REQUEST_DEVICES_HOST+DEVICE_REGISTER_API+COMMON_PARAMS;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Url);
        stringBuilder.append("&os_api="+douyinAccount.getOs_api());
        stringBuilder.append("&resolution="+douyinAccount.getResolution());
        stringBuilder.append("&dpi="+douyinAccount.getDpi());
        return  stringBuilder.toString();
    }
    public static String getDeviceTime(String baseInfoURL){
        return baseInfoURL+"&_rticket="+ParamCreater.get_Rticket();
    }
    public static String getParmsCreaterByUs(String baseInfoURL, Map device_Info_Map){
        return baseInfoURL+DeviceInfoCreater.getWholeStr(device_Info_Map);
    }

    private static String getTestURL(){
        DouyinAccountPo douyinAccountPo =new DouyinAccountPo();
        douyinAccountPo.setDeviceCookie("1233");
        douyinAccountPo.setDevice_brand("32323");
        douyinAccountPo.setDevice_type("xiaomi");
        douyinAccountPo.setIid("fsdfsdf");
        douyinAccountPo.setOpenudid("fsdfsdf");
        douyinAccountPo.setUuid("fwefwfv");
        douyinAccountPo.setDevices_id("cescescc");
        String result = getDeviceBaseURL(douyinAccountPo);
        result = getParmsCreaterByUs(result,DeviceInfoCreater.getResult());
        return getDeviceTime(result);
    }

    public static void main(String[] args){
        System.out.println(getTestURL());
    }
    /**
     * 发送设备信息，获取设备cookie码
     * @return 对应的request请求
     * @throws IOException
     */
    public static Request send(String strurl) throws IOException{

        FormBody.Builder params=new FormBody.Builder();
        // 构建GET请求，并设置User-Agent请求消息头和Cookie请求消息头
        Request request = new Request.Builder()
                .url("")
                .addHeader("Host", "ib.snssdk.com")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("User-Agent", "okhttp/3.7.0.6")
                .addHeader("Cache-Control","max-stale=0")
                .get()
                .build();

        return request;
    }
}
