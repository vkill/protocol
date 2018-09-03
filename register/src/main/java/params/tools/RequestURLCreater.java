package params.tools;

import enums.*;
import enums.paramtable.DirTable;
import jsonreader.tools.JsonTableGetter;
import org.apache.catalina.User;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：GXDTJJC
 * @ Date       ：Created in 18:20 2018/8/30/030
 * @ Description  模拟生成模拟器请求方法类
 * @ Modified By：
 * @Version: $version$
 */
public class RequestURLCreater {
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

    public static String getSendMessageFromMap(String host,String msg,Map<String,String> messageInfoMap) {
        StringBuilder stringBuilder = new StringBuilder(host + msg);
        for (SendMessageInfo sendMessageInfo : SendMessageInfo.values()) {
            String key_Str = sendMessageInfo.getVaule();
            //System.out.println(key_Str);
            if (sendMessageInfo.equals(SendMessageInfo.os_api)) {
                if (messageInfoMap.containsKey(key_Str)) {
                    stringBuilder.append(key_Str + "=" + messageInfoMap.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.append(key_Str + "=" + DirTable.base_Sendmessage_Info.get(sendMessageInfo));
                }
            } else {
                if (messageInfoMap.containsKey(key_Str)) {
                    stringBuilder.append("&" + key_Str + "=" + messageInfoMap.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.append("&" + key_Str + "=" + DirTable.base_Sendmessage_Info.get(sendMessageInfo));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String getRegisterInfoFromMap(String host,String msg,Map<String,String> messageInfoMap) {
        StringBuilder stringBuilder = new StringBuilder(host + msg);
        for (UserRegisterInfo userRegisterInfo : UserRegisterInfo.values()) {
            String key_Str = userRegisterInfo.getVaule();
            System.out.println(key_Str);
            if (userRegisterInfo.equals(UserRegisterInfo.os_api)) {
                if (messageInfoMap.containsKey(key_Str)) {
                    stringBuilder.append(key_Str + "=" + messageInfoMap.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.append(key_Str + "=" + DirTable.base_UserRegister_Info.get(userRegisterInfo));
                }
            } else {
                if (messageInfoMap.containsKey(key_Str)) {
                    stringBuilder.append("&" + key_Str + "=" + messageInfoMap.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.append("&" + key_Str + "=" + DirTable.base_UserRegister_Info.get(userRegisterInfo));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static Map<String, String> getBodyForMessage(Map<String,String> messages){
        Map<String,String> stringBuilder = new HashMap<String,String>();
        for (SendMessageBodyInfo sendMessageInfo : SendMessageBodyInfo.values()) {
            String key_Str = sendMessageInfo.getVaule();
            //System.out.println(key_Str);
            if (sendMessageInfo.equals(SendMessageBodyInfo.mix_mode)) {
                if (messages.containsKey(key_Str)) {
                    stringBuilder.put(key_Str,messages.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    Map map = DirTable.sendMessageBodyInfoStringMap;
                    stringBuilder.put(key_Str,DirTable.sendMessageBodyInfoStringMap.get(sendMessageInfo));
                }
            } else {
                if (messages.containsKey(key_Str)) {
                    stringBuilder.put(key_Str,messages.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.put(key_Str,DirTable.sendMessageBodyInfoStringMap.get(sendMessageInfo));
                }
            }
        }
        return stringBuilder;
    }

    public static Map<String,String> getBodyForRegister(Map<String,String> messages){
        Map<String,String> stringBuilder = new HashMap<String, String>();
        for (UserRegisterBodyInfo sendMessageInfo : UserRegisterBodyInfo.values()) {
            String key_Str = sendMessageInfo.getVaule();
            System.out.println(key_Str);
            if (sendMessageInfo.equals(UserRegisterBodyInfo.mix_mode)) {
                if (messages.containsKey(key_Str)) {
                    stringBuilder.put(key_Str , messages.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.put(key_Str , DirTable.userRegisterBodyInfoStringMap.get(sendMessageInfo));
                }
            } else {
                if (messages.containsKey(key_Str)) {
                    stringBuilder.put( key_Str , messages.get(key_Str));
                    //System.out.println(key_Str+"  json  "+jsonMap.get(key_Str));
                } else {
                    stringBuilder.put( key_Str ,DirTable.userRegisterBodyInfoStringMap.get(sendMessageInfo));
                }
            }
        }
        return stringBuilder;
    }
}
