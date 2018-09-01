package params.tools;

import params.ParamCreater;
import po.DouyinAccountPo;

/**
 * 生成url的
 */
public class GenerateUrl {

    private static final String REQUEST_DEVICES_HOST = "https://ib.snssdk.com";

    private static final String DEVICE_REGISTER_API = "/service/2/device_register/?";

    private static final String COMMON_PARAMS = "ac=wifi&app_name=aweme&version_code=176&version_name=1.7.6&manifest_version_code=176&device_platform=android&ssmix=a&language=zh&";

    public static String getRegisterDeviceUrl(DouyinAccountPo douyinAccount) {
        if (douyinAccount == null) {
            return "";
        }
        String params ="os_api"+ douyinAccount.getOs_api()+ "&openudid="+douyinAccount.getOpenudid()+"&uuid=" + douyinAccount.getUuid() + "&resolution=" + douyinAccount.getResolution();
        return REQUEST_DEVICES_HOST + DEVICE_REGISTER_API + COMMON_PARAMS + params;
    }

    public static String getTimeInfo(String deviceUrl){
        String _rticket = ParamCreater.get_Rticket();
        String ts = ParamCreater.get_Ts(_rticket);
        return deviceUrl+"&_rticket="+_rticket+"&t";
    }
    public static void main(String[] args) {
        DouyinAccountPo dy = new DouyinAccountPo();
        dy.setOs_api("22");
        dy.setUuid("11111111");
        dy.setResolution("900*1600");
        dy.setDpi("240");
        System.out.println(getRegisterDeviceUrl(dy));;

    }
}
