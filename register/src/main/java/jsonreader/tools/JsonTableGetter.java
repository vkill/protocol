package jsonreader.tools;

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
            result2.put("openudid",mapper.get("openudid"));
            result2.put("udid",mapper.get("uuid"));


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

}
