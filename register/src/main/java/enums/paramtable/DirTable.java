package enums.paramtable;

import enums.*;
import enums.paramtable.urltools.URLmakeTools;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: protocol
 * @description: 静态表格文件static存储
 * @author: gaoxiang
 * @create: 2018-08-26 13:32
 **/
public class DirTable {
    public static HashMap<BaseNum,String> phone_Table = new HashMap<BaseNum, String>();
    public static HashMap<BaseAppInfo,String> base_APPInfo_Table = new HashMap<BaseAppInfo,String>();
    public static Map base_DeviceRegister_Info = new HashMap<DeviceRegisterInfo,String>();
    public static Map<SendMessageInfo,String> base_Sendmessage_Info = new HashMap<SendMessageInfo,String>();
    public static Map<UserRegisterInfo,String> base_UserRegister_Info = new HashMap<UserRegisterInfo,String>();
    public static Map<UserRegisterBodyInfo,String> userRegisterBodyInfoStringMap = new HashMap<UserRegisterBodyInfo,String>();
    public static Map<SendMessageBodyInfo,String> sendMessageBodyInfoStringMap = new HashMap<SendMessageBodyInfo,String>();
    static{
        phone_Table.put(BaseNum.zero,"35");
        phone_Table.put(BaseNum.one,"34");
        phone_Table.put(BaseNum.two,"37");
        phone_Table.put(BaseNum.three,"36");
        phone_Table.put(BaseNum.four,"31");
        phone_Table.put(BaseNum.five,"30");
        phone_Table.put(BaseNum.six,"33");
        phone_Table.put(BaseNum.serven,"32");
        phone_Table.put(BaseNum.eight,"3d");
        phone_Table.put(BaseNum.nine,"3c");
        phone_Table.put(BaseNum.plus,"2e");
        phone_Table.put(BaseNum.blank,"25");
        phone_Table.put(BaseNum.star,"2f");
    }

    static{
        base_APPInfo_Table.put(BaseAppInfo.version_name,"2.3.0");
        base_APPInfo_Table.put(BaseAppInfo.version_code,"230");
        base_APPInfo_Table.put(BaseAppInfo.update_version_code,"2302");
        base_APPInfo_Table.put(BaseAppInfo.unbind_exist,"0");
        base_APPInfo_Table.put(BaseAppInfo.type,"3731");
        base_APPInfo_Table.put(BaseAppInfo.ticket,"");
        base_APPInfo_Table.put(BaseAppInfo.ssmix,"a");
        base_APPInfo_Table.put(BaseAppInfo.scene,"0");
        base_APPInfo_Table.put(BaseAppInfo.retry_type,"no_retry");
        base_APPInfo_Table.put(BaseAppInfo.resolution,"1400*900");
        base_APPInfo_Table.put(BaseAppInfo.os_version,"5.1.1");
        base_APPInfo_Table.put(BaseAppInfo.os_api,"22");
        base_APPInfo_Table.put(BaseAppInfo.openudid,"f8d10149126e89d9");
        base_APPInfo_Table.put(BaseAppInfo.mix_mode,"1");
        base_APPInfo_Table.put(BaseAppInfo.manifest_version_code,"230");
        base_APPInfo_Table.put(BaseAppInfo.language,"zh");
        //模拟器或者app相关属性
        base_APPInfo_Table.put(BaseAppInfo.iid,"42055406212");
        base_APPInfo_Table.put(BaseAppInfo.dpi,"240");
        base_APPInfo_Table.put(BaseAppInfo.device_platform,"android");
        //模拟器相关属性
        base_APPInfo_Table.put(BaseAppInfo.device_id,"5663125458");
        base_APPInfo_Table.put(BaseAppInfo.channel,"xiaomi");
        base_APPInfo_Table.put(BaseAppInfo.captcha,"");
        base_APPInfo_Table.put(BaseAppInfo.app_name,"aweme");
        base_APPInfo_Table.put(BaseAppInfo.aid,"1128");
        base_APPInfo_Table.put(BaseAppInfo.ac,"wifi");
    }

    static{
        String buff="ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type=vivo+y51a&device_brand=vivo&language=zh&os_api=22&os_version=5.1.1&uuid=865166029262923&openudid=b52b1961bfc67907&manifest_version_code=176&resolution=720*1280&dpi=240&update_version_code=1762&_rticket=1535619578298&tt_data=a \n";
        Map map = URLmakeTools.url_split(buff);
        for(DeviceRegisterInfo deviceRegisterInfo:DeviceRegisterInfo.values()){
            String vaule_Buff = (String) map.get(deviceRegisterInfo.getVaule());
            //System.out.println(deviceRegisterInfo.getVaule()+"   "+vaule_Buff);
            base_DeviceRegister_Info.put(deviceRegisterInfo,vaule_Buff);
        }
    }


    static{
        String buff="https://is.snssdk.com/passport/mobile/send_code/?os_api=22&device_type=vivo+y51a&device_platform=android&ssmix=a&iid=42690885679" +
                "&manifest_version_code=176&dpi=240&uuid=865166029262923&version_code=176&app_name=aweme&version_name=1.7.6&" +
                "openudid=b52b1961bfc67907&device_id=38678816574&resolution=1280*720&os_version=5.1.1&language=zh&device_brand=vivo" +
                "&ac=wifi&update_version_code=1762&aid=1128&channel=tengxun&_rticket=1535858139263&ts=1535858138&as=a1f565a87aad5b058b3210" +
                "&cp=5dd1b15ea1be8958e1qber&mas=000cead8f97453930c880f7f9b771e31820c8c4ccc461cac0c46ac";
        Map map = URLmakeTools.url_split(buff);
        for(SendMessageInfo baseSendMessageInfo: SendMessageInfo.values()){
            String vaule_Buff = (String) map.get(baseSendMessageInfo.getVaule());
            //System.out.println(deviceRegisterInfo.getVaule()+"   "+vaule_Buff);
            base_Sendmessage_Info.put(baseSendMessageInfo,vaule_Buff);
        }
    }

    static{
        String buff="https://is.snssdk.com/passport/mobile/register/?os_api=22&device_type=vivo+y51a&device_platform=android&ssmix=a&iid=42690885679&manifest_version_code=176" +
                "&dpi=240&uuid=865166029262923&version_code=176&app_name=aweme&version_name=1.7.6&openudid=b52b1961bfc67907&device_id=38678816574&resolution=1280*720&os_version=5.1.1" +
                "&language=zh&device_brand=vivo&ac=wifi&update_version_code=1762&aid=1128&channel=tengxun&_rticket=1535858363961&ts=1535858363&as=a1154508eb0b1b06cb2458" +
                "&cp=5db4bb5dbab18968e1wdeh&mas=00e9d5ee6bd7691f8ec958b1a3b3daf3b01cac2c4c46c66c0c468c";
        Map map = URLmakeTools.url_split(buff);
        for(UserRegisterInfo userRegisterInfo: UserRegisterInfo.values()){
            String vaule_Buff = (String) map.get(userRegisterInfo.getVaule());
            //System.out.println(deviceRegisterInfo.getVaule()+"   "+vaule_Buff);
            base_DeviceRegister_Info.put(userRegisterInfo,vaule_Buff);
        }
    }

    static{
        String buff="mix_mode=1&type=34&mobile=2e3d333734363437363734363734&retry_type=no_retry&os_api=22&device_type=vivo y51a&device_platform=android&ssmix=a&iid=43016694171" +
                "&manifest_version_code=176&dpi=240&uuid=865166029262923&version_code=176&app_name=aweme&version_name=1.7.6&openudid=b52b1961bfc67907&device_id=38678816574&resolution=720*1280" +
                "&os_version=5.1.1&language=zh&device_brand=vivo&ac=wifi&update_version_code=1762&aid=1128&channel=tengxun&_rticket=1535888597105";
        Map map = URLmakeTools.url_split(buff);
        for(SendMessageBodyInfo sendMessageBodyInfo: SendMessageBodyInfo.values()){
            String vaule_Buff = (String) map.get(sendMessageBodyInfo.getVaule());
            //System.out.println(deviceRegisterInfo.getVaule()+"   "+vaule_Buff);
            base_DeviceRegister_Info.put(sendMessageBodyInfo,vaule_Buff);
        }
    }

    static{
        String buff="password=3437363734363734363734363734&mix_mode=1&type=36&code=36343736&mobile=2e3d333734363437363734363734&retry_type=no_retry&os_api=22&device_type=vivo y51a" +
                "&device_platform=android&ssmix=a&iid=43016694171&manifest_version_code=176&dpi=240&uuid=865166029262923&version_code=176&app_name=aweme&version_name=1.7.6" +
                "&openudid=b52b1961bfc67907&device_id=38678816574&resolution=720*1280&os_version=5.1.1&language=zh&device_brand=vivo&ac=wifi" +
                "&update_version_code=1762&aid=1128&channel=tengxun&_rticket=1535888618988";
        Map map = URLmakeTools.url_split(buff);
        for(UserRegisterBodyInfo userRegisterBodyInfo: UserRegisterBodyInfo.values()){
            String vaule_Buff = (String) map.get(userRegisterBodyInfo.getVaule());
            //System.out.println(deviceRegisterInfo.getVaule()+"   "+vaule_Buff);
            userRegisterBodyInfoStringMap.put(userRegisterBodyInfo,vaule_Buff);
        }
    }

    public static void main(String[]args){
        String buff="ac=wifi&channel=tengxun&aid=1128&app_name=aweme&version_code=176&version_name=1.7.6&device_platform=android&ssmix=a&device_type=vivo+y51a&device_brand=vivo&language=zh&os_api=22&os_version=5.1.1&uuid=865166029262923&openudid=b52b1961bfc67907&manifest_version_code=176&resolution=720*1280&dpi=240&update_version_code=1762&_rticket=1535619578298&tt_data=a \n";
        Map map = URLmakeTools.url_split(buff);
        for(Object key:map.keySet()){
            System.out.println(key+" kao "+map.get(key));
        }
    }

}
