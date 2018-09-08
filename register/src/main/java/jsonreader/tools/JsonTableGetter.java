package jsonreader.tools;

import org.apache.commons.codec.binary.Base64;
import util.MD5Code;
import org.json.JSONException;
import org.json.JSONObject;
import params.ParamCreater;

import java.lang.reflect.Method;
import java.util.*;

public class JsonTableGetter {

    /**
     * @param json 需要转变的json
     * @return json解析切返回的map类型数据
     */
    public static Map<String,String> JsonToMap(JSONObject json){
        Iterator<String> iter = json.keys();
        Map result = new HashMap<String,String>();
        try {
            while (iter.hasNext()) {
                String key = iter.next();
                if(!key.equals("header")){
                    result.put(key, json.getString(key));
                }
                else{
                    String value = String.valueOf(json.getString(key));
                    char []temp = value.toCharArray();
                    String temp1 = "";
                    for(int i = 1;i < temp.length - 1;i++){
                        temp1 += temp[i];
                    }
                    String []temp2 = temp1.split(",");
                    for(int i = 0;i < temp2.length;i++){
                        String []temp3 = temp2[i].split(":");
                        result.put(temp3[0], temp3[1]);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return 使用参数构造的JSon
     */
    public static JSONObject construtJson(){

        Map mapper = DeviceInfoCreater.getResult();

        JSONObject result1 = new JSONObject(new LinkedHashMap());
        JSONObject result2 = new JSONObject(new LinkedHashMap());

        try {
            result2.put("display_name","抖音短视频");
            result2.put("update_version_code",1762);
            result2.put("manifest_version_code",176);
            result2.put("aid",1128);
            result2.put("channel","baidu");
            result2.put("appkey","57bfa27c67e58e7d920028d3");
            result2.put("package","com.ss.android.ugc.aweme");
            result2.put("app_version","1.8.3");
            result2.put("version_code",176);
            result2.put("sdk_version",201);
            result2.put("os","Android");
            result2.put("os_version","5.1.1");
            result2.put("os_api",22);

            //从这里开始的手机型号可能需要跟其他方法相同
            result2.put("device_model",mapper.get("device_type"));
            result2.put("device_brand",mapper.get("device_brand"));
            result2.put("device_manufacturer",mapper.get("device_brand"));

            result2.put("cpu_abi","armeabi-v7a");

            //clientudid和serial number采用的是随机数加md5生成，不一定正确
            String uuid_temp = String.valueOf(new Random(System.currentTimeMillis()).nextInt(9999999));
            String line = new MD5Code().getMD5ofStr(uuid_temp);
            String []temp = line.split("");
            String clientudid = "";
            for(int i = 0;i < temp.length;i++){
                clientudid += temp[i];
                if(i == 7 || i == 11 || i ==15 || i ==19){
                    clientudid += "-";
                }
            }
            //"f65e09f1-f0e7-47ad-a2f0-0d53074def1b"
            result2.put("clientudid",clientudid.toLowerCase());
            String serial_number = "";
            for(int i = 0; i < 8;i++){
                int temp_num = new Random(System.currentTimeMillis()).nextInt(31);
                serial_number += String.valueOf(temp[temp_num]);
            }
            serial_number = serial_number.toLowerCase();
            //"009a7ba9"
            result2.put("build_serial",serial_number);
            result2.put("release_build","61b8304_20180522");
            result2.put("density_dpi",240);
            result2.put("display_density","hdpi");
            result2.put("resolution","1280x720");
            result2.put("language","zh");

            //ma调用方法随机生成
            result2.put("mc",randomMac4Qemu());

            result2.put("timezone",8);
            result2.put("access","wifi");
            result2.put("not_request_sender",0);
            result2.put("carrier","CHINA MOBILE");
            result2.put("mcc_mnc","46000");
            result2.put("rom","V9.5.2.0.LACCNFA");
            result2.put("rom_version","coloros__LMY48Z");
            result2.put("sig_hash","aea615ab910015038f73c47e45d21466");
//            result2.put("openudid",mapper.get("openudid"));
//            result2.put("udid",mapper.get("uuid"));
            result2.put("openudid","dd516d7cytre3428");
            result2.put("udid","865166026757198");

            result2.put("serial_number",serial_number);
            ArrayList<String> number = new ArrayList<String>();
            result2.put("sim_serial_number",number);
            result2.put("region","CN");
            result2.put("tz_name","Asia/Shanghai");
            result2.put("tz_offset",28800);
            result2.put("sim_region","cn");

            result1.put("magic_tag","ss_app_log");
            result1.put("header",result2);
            result1.put("_gen_time", ParamCreater.get_Rticket());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result1;
    }

    /**
     *
     * @param json 需要转变为map的json
     * @return json 转成的map 子类型的json没有解析成键值对
     */
    public Map<String,String> parseOptions(JSONObject json)  {
        Map<String, String>  options = new HashMap<String, String>();
        Iterator<String> iter = json.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            try {
                options.put(key, json.getString(key));
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("获取json "+key+"值出错");
            }
        }
        return options;
    }

    public static String randomMac4Qemu() {
        String SEPARATOR_OF_MAC = ":";
        Random random = new Random();
        String[] mac = {
                String.format("%02x", 0x52),
                String.format("%02x", 0x54),
                String.format("%02x", 0x00),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff))
        };
        return String.join(SEPARATOR_OF_MAC, mac);
    }

    private static String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialnocustom");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }

    public static void main(String[]args){
        String gzipString = "dRHvv73vv73vv71F77+977+9G3gjDlZB77+9XjFo77+9QQgS77+9FS4eB3sL77+9M14JDTjvv70p" +
                "du+/ve+/vRTvv71yY++/ve+/vVkn77+9NUtULVp8Me+/vWrvv70yZ0Yg77+9J++/ve+/vVPNgO+/" +
                "vVrvv71777+977+9Gk9z77+9HhAMIh3vv71h77+977+9Oznvv71n77+977+93Jzvv70x77+977+9" +
                "77+977+9YFjvv73vv70d77+9CO+/vQgB77+9PO+/ve+/vWBQLD9x77+9Je+/vXzvv73vv73vv70J" +
                "77+977+9Fe+/ve+/ve+/vQdl77+977+977+9We+/ve+/vQZsBe+/vSkGRUpLN++/ve+/vRZZ3pvv" +
                "v71UL++/ve+/vTNv77+9UO+/vRnvv71877+977+9D++/vQDvv71G77+977+9ehPvv70yIWvvv73f" +
                "te+/vWDvv71X77+977+9Ry0Y77+9Oe+/vT5QSu+/vW5g77+977+9e0nGpO+/vWsB77+9cu+/vXzR" +
                "l++/ve+/ve+/ve+/vWzvv73vv73vv71T77+977+977+9WkZq77+977+977+9CO+/ve+/ve+/vQNM" +
                "IjHvv73vv71N77+9ya/vv70ebu+/vW7vv73vv713Ux0aXURg3b/vv70a77+977+977+9Uz9SfUzv" +
                "v70177+9Be+/vUI/77+9G8uaa++/ve+/ve+/ve+/ve+/ve+/vRUB77+977+9ae+/ve+/vSXvv73v" +
                "v715Z2Dvv71yH3Xvv70t77+977+9M++/vX8/77+977+977+9Ye+/vUc777+9LnTvv70MQA8jJe+/" +
                "ve+/vdiJ77+977+9aRrvv70LT++/ve+/vWfvv73vv70v77+977+9Wk4q77+9bV4rfSkcce+/ve+/" +
                "ve+/vWhgPRd60rDvv73PlQjvv73vv71RDTNa77+9Ue+/vVcm77+9Ku+/ve+/vWEtKz5+Te+/vU7v" +
                "v71x3YDvv73vv73vv71Gd++/vR4T26Xvv73vv70XxbYU77+9TFrvv71GSQ7vv73vv73Ql++/ve+/" +
                "vV4vyoTlq4ZIHe+/vVnvv71j77+9yadqW++/vR3vv73vv70u77+9eGo+77+9Qe+/vREaQxcCSO+/" +
                "vS9W77+977+9e1Tvv73vv717ZEfvv70CJO+/vcO777+977+9M0scAe+/vSsz77+9W++/ve+/vV/v" +
                "v73vv73vv73vv73vv703JO+/ve+/ve+/vSLvv73vv70Cd3bvv73vv70Y77+9S++/vQrvv73VlzEA" +
                "77+9W++/vTnXiO+/ve+/vUDvv71z77+9M++/ve+/vS8177+977+9DO+/ve+/vVLvv70b77+977+9" +
                "Qe+/vR1PyZIFUc2H77+9UjZ1eknvv73vv71N77+9PEkwFS3vv73vv70/77+977+9X++/ve+/vUxw" +
                "77+977+977+977+977+9C3Hvv71I77+977+9KkczLiYvOu+/vdKF77+977+977+9PO+/ve+/vVXv" +
                "v71G77+9GUdUf3rvv73vv70v77+9de+/vXBm77+9RlVQedq6DkPvv73dmB3vv73vv71NSu+/vTVe" +
                "77+977+977+9Pe+/ve+/ve+/ve+/vRrvv73vv73vv73vv73vv73vv71J77+9F++/ve+/vRkaB++/" +
                "ve+/ve+/vTsfT2U577+977+977+9CO+/vRcB77+977+9bV94Re+/vQIx77+977+9NhHvv73vv71k" +
                "Fe+/ve+/vUZD77+9axvvv73vv70G77+93apfUGkS77+9Le+/vRDvv73vv70D77+977+977+977+9" +
                "15vvv714e1vvv73vv71677+9UO+/ve+/ve+/ve+/ve+/vX4ze33vv71yYe+/vTTvv70d77+9b05m" +
                "J3UD77+977+9RB4B77+9SV3vv70hHWjvv73WmO+/ve+/vXjvv73vv71E77+9Ie+/ve+/vXLvv73v" +
                "v70m77+9GSoO77+9z6UU77+9M2xd77+9Xu+/vRPlrJp677+9THYxNSpQdu+/vRwKc++/ve+/vSlE" +
                "07bvv70H16rvv73vv71P77+9ECdZ77+977+9IhXvv71MZe+/vSd+77+9FO+/vW4+77+977+9W++/" +
                "vWfvv73vv70s77+977+9Ye+/vQjvv70p77+977+977+977+9Zybvv71o77+9GXQs77+977+9Ye+/" +
                "vWdx77+9MRlOBe+/vV59Sjx7Ru+/vQYlEQIh77+9MdC077+9Oe+/vSYa77+9HT8/Ru+/ve+/ve+/" +
                "vUYL77+9RCJjL2ALJRzvv73vv70cGVoY77+9VSkvUu+/ve+/ve+/vRczOUcq77+9AD8277+9TWPv" +
                "v73vv71AUDwx77+977+9W++/ve+/ve+/ve+/vVk177+9K++/vWrGsyILU++/vSzvv71wd0Dvv73v" +
                "v73vv706WuOrj++/ve+/ve+/ve+/ve+/vUbvv71h77+977+9Ru+/vSbvv71YGBXvv70r77+9N++/" +
                "ve+/ve+/vX1q77+9EWnvv71eBnHvv73vv71b77+977+9KmMIOE3vv70777+9SWhc77+9Gu+/ve+/" +
                "vUHvv71M77+977+977+9FV7vv70Z77+9T2zvv73vv73vv71qLCnvv70NEjXvv73vv71HDw4oFe+/" +
                "vQBX77+9YxLvv73vv73vv71n77+9Re+/vQ7vv73vv70CTe+/ve+/ve+/vSdh77+9woHVvBtyM++/" +
                "vSEy77+9TgnOhzNC77+977+9ce+/vXrvv73vv73vv73vv71L77+9VlDvv71GEu+/ve+/ve+/vVYU" +
                "VytT77+9F2jvv73vv73vv71sIAx977+9GgLvv73vv70177+9aO+/vQ3vv73vv70C77+977+977+9" +
                "I2l377+977+977+9ee+/ve+/vQXvv73vv73vv71dP++/vSHvv73vv70I77+9YHTEgn4VHwrvv70L" +
                "77+9dO+/vQjvv73ihJ4877+9ADcPDd+XaBfdve+/vUUz77+977+9Ju+/vSF077+9VAPvv70A77+9" +
                "C9qR77+977+977+9xrjvv71lFO+/vX/oh704a++/vWnvv719NDHvv70F77+92prvv71xAirvv73v" +
                "v70v77+9Ykjvv73vv71sUTDvv70f77+9HmkGBe+/vWxH77+977+9Ie+/ve+/ve+/vQHGnu+/vUbv" +
                "v73vv71Tfu+/ve+/vWga77+9WhdNBe+/ve+/vQlNWe+/vSsE77+977+9X++/ve+/ve+/ve+/ve+/" +
                "vVdz77+9Tu+/ve+/vSvvv71+77+977+9Tgbvv73vv73vv71yTXsQ77+90bN6MTHvv71K77+9ZiPv" +
                "v71T77+977+9LO+/vT1Ndu+/vSjvv71s77+9J++/ve+/ve+/vVrvv73vv73vv71+fAPvv73vv73v" +
                "v73vv70w77+9fvSBp6bvv71o77+9XGJo77+9Re+/vR9HD++/ve+/ve+/vUtAd++/ve+/vUvvv713" +
                "TDLvv73vv71gczVMU9WL77+9wqlxGwkGXGDvv70K77+9CzJ/U++/vXAcx5hIFO+/ve+/vV7vv73v" +
                "v73qtJpEQS0877+977+977+977+9Ck7vv70077+9V++/ve+/vWcGOytD77+9Rm80VxXbju+/veiD" +
                "te+/ve+/ve+/vRY977+9dO+/vQ8477+9X3nvv70277+977+9D++/vS58Zu+Xhu+/ve+/vW48Vu+/" +
                "ve+/vSHvv73vv70IIdqV77+9Cwrvv71S77+9dg93Bu+/vW7vv73vv71j77+9A07vv71QCe+/ve+/" +
                "vWzvv70EHiQn77+9L++/vWMgau+/ve+/vWghM++/ve+/ve+/ve+/ve+/vRgcMmDvv702LWgB77+9" +
                "77+9W3cX77+9Yu+/ve+/vSXvv73vv70OWe+/ve+/vXtPJE/vv73vv73vv70/77+977+977+9D3Lv" +
                "v71i77+977+9DO+/vS5VIu+/vVDvv73vv71qT++/ve+/vXPvv71D77+9LD3HmR7vv70kN1F+OFnv" +
                "v73vv73vv73vv70udO+/vX3vv73vv70Sfe+/vURuJ++/vW4U1o7vv71o77+9NiPvv73vv73vv71D" +
                "Xu+/ve+/vXRJ77+977+93qjvv73vv70X77+9Gu+/vTYyWO+/vVUC77+9BQAX77+9KxXCnO+/ve+/" +
                "ve+/ve+/vXN1cQIICO+/ve+/vRjvv71377+977+977+9MjHvv73vv70m77+9A++/ve+/vcSIZO+/" +
                "vRx8aO+/vWhw77+9ZO+/vSFlM++/ve+/ve+/vS3vv73EnQ9IU0dk77+977+9M++/vUnvv71a77+9" +
                "77+977+977+92ZXvv73vv73vv71SbGND77+9Hz4pdO+/ve+/vWM977+9EnM177+9Hu+/vRXvv70W" +
                "77+976KA77+977+977+9au+/ve+/ve+/vU7vv73vv73vv71W77+9PTow77+9WFI677+9QiZbfSjv" +
                "v73vv73vv71YSz/vv70BP19P77+9eO+/ve+/vRYPf3Xvv70hUjsp77+977+9GBPvv73vv73vv73v" +
                "v71z77+9FGVuXO+/ve+/ve+/ve+/vVPvv71r64K7Txfvv73vv70f77+977+91prvv70B77+977+9" +
                "77+9DyVE77+9BQhaJDJw77+977+977+977+9CO+/ve+/ve+/ve+/ve+/vQ7vv73vv70YZV5c77+9" +
                "77+9E1Tvv71GXe+/vdGj77+9GDxcDmfvv71IAgTvv71377+9dxhQW++/vSUX77+9VGlWJ++/vRzv" +
                "v71K77+9ee+/vXQfAu+/vTPvv73Cq++/vRETaXXvv73vv70hDRAaRO+/vXYMWO+/vXvvv71g77+9" +
                "77+9cx7vv715Ce+/vV3vv71/77+977+9N++/ve+/vR/vv73vv702T++/vRtiRe+/vQ9ORSF377+9" +
                "77+977+9YO+/vV7vv73vv71ey6jYsu+/vSHvv71R77+977+977+9dVp177+977+977+977+977+9" +
                "XRJRfO+/vQEE77+9Yu+/vRASdixe77+977+9M++/ve+/ve+/ve+/vTDqnpAg77+9C++/ve+/ve+/" +
                "ve+/ve+/ve+/vWIneu+/vRnvv73vv73nr5Xvv73vv73vv73vv73vv73vv707XO+/vTgVF35pQVDv" +
                "v73vv73vv71R77+9IQNJ77+977+977+947W077+9aAdTcHPvv73vv73vv73CqO+/ve+/vSwL77+9" +
                "fTDvv71O77+977+9Me+/vTfvv70R77+977+977+9bu+/ve+/vSUe77+977+9wogT77+9LlwnTO+/" +
                "ve+/ve+/vRkaZ3Pvv71m77+9Uu+/ve+/vUnvv71mXO+/ve+/vRPvv73vv73vv73vv71pxqhFTx1H" +
                "Oe+/ve+/ve+/vVYx77+9GO+/ve+/ve+/vTTvv70wAO+/vVPvv70t1a0D77+9";

        System.out.println(GzipGetteer.uncompressToString(Base64.decodeBase64(gzipString)));
    }
}
