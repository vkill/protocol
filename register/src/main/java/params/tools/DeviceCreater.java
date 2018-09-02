package params.tools;

import enums.DeviceRegisterInfo;
import enums.paramtable.DirTable;
import jsonreader.tools.JsonTableGetter;
import org.json.JSONObject;

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

    public static String getUrlFromJsonAndMap(JSONObject jsonObject){
        Map <String,String> jsonMap = JsonTableGetter.JsonToMap(jsonObject);
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


}
