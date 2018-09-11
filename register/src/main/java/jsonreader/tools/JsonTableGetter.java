package jsonreader.tools;

import com.space.register.entity.DeviceEntity;
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
    private static String SEPARATOR_OF_MAC = ":";

    private static String randomMac4Qemu() {
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

    public static JSONObject contrustJsonForReal(DeviceEntity deviceEntity){



        JSONObject result = new JSONObject();
        String mac = getMac();
        try {
            result.put("imei",deviceEntity.getUuid());
            result.put("imsi",getImsi());
            result.put("iccid",getIssid());
            result.put("cpu","abi: armeabi-v7anProcessor\t: AArch64 Processor rev 2 (aarch64)\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\n\nHardware\t: MT6795T\nRevision\t: 5753\n");
            result.put("uid",getRandom1());
            result.put("wifimac","02:00:00:00:00:00");
            result.put("type",3);
            result.put("idfa",deviceEntity.getOpenudid());
            result.put("file","meta_init.rc,meta_init.project.rc,meta_init.modem.rc,init.xlog.rc,init.ssd.rc,init.project.rc,init.mt6795.usb.rc,init.mt6795.rc,init.mt6595.rc,init.modem.rc,init.environ.rc,init.aee.rc,fstab.mt6795,factory_init.rc,factory_init.project.rc,enableswap.sh,");
            result.put("fingerprint","Meizu/meizu_mx5/mx5:5.1/LMY47I/1517208287:user/release-keys");
            result.put("description","meizu_mx5-user 5.1 LMY47I 1517208287 release-keys");
            result.put("temperature",1);
            result.put( "mem",Long.parseLong(getSDTotal()));
            result.put("sdtotal",Long.parseLong("10618138624"));
            result.put("sdused",Long.parseLong(getSDTotal()));
            result.put( "battery",getRandom2());


            result.put("charge",0);
            result.put("os","5.1");
            result.put("display","1080,1920");
            result.put("root",0);
            result.put( "time",Long.parseLong(ParamCreater.get_Rticket()));
            result.put("active",getRandom1());
            result.put("lock",5000);
            result.put("brand", deviceEntity.getDevice_type());
            result.put("vpn",0);
            result.put("host",1);
            result.put("mac","F4:F5:DB:19:78:22");
            result.put("cellid",0);
            result.put("provider", "中国移动");
            result.put("wifissid","\"1604穿透强\"");
            result.put("wifibssid", "dc:fe:18:62:f2:f5");
            result.put("h","");
            result.put("h","eeef2000-eef42000 r--p 00000000 b3:14 499773 /data/dalvik-cache/arm/system@framework@XposedBridge.jar@classes.dex\n");
            result.put("wifiip","192.168.0.155");
            result.put("sim",1);


            ArrayList<String> list1 = new ArrayList<String>();
            ArrayList<String> list2 = new ArrayList<String>();
            String []temp = {
                    "com.baidu.BaiduMap",
                    "com.tencent.mm",
                    "com.qq.ac.android",
                    "com.qiyi.video",
                    "com.tencent.mobileqq",
                    "com.baidu.searchbox",
                    "com.tencent.qqmusic",
                    "com.baidu.netdisk",
                    "com.tencent.qqlive",
                    "com.ss.android.ugc.aweme",
            };
            String [] temp1 = {"cd20da89dcec5d13cc60d4edbd6f8201",
                    "886378b839b379a64f312a0c2fa929e0",
                    "774054585baf5d351c4080b4e54c9871",
                    "0afcd7b628f4c0e7573e7dfcfc325f02",
                    "f9b5ba16d73b671d3a95bb66e6103c09",
                    "20d2f1c97d4f6ce609b930627231c2c7",
                    "9edf3c656072a6104179699f0d5f2d77",
                    "a900b3fe745baa0f1f194354cc610700",
                    "4cdcf818f49bed8a639e38b942d3fcc7",
                    "5bbb0f1ae9dfd172e695397ee2f66fff"};
            for(int i = 0;i < temp.length;i++){
                list1.add(temp[i]);
            }
            for(int i = 0;i < temp1.length;i++){
                list2.add(temp1[i]);
            }

            result.put("applist",list1);
            result.put("apkcount","10");

            //result.put("photolist",list2);

            //System.out.println(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getImsi() {
        String title = "5200";
        int second = 0;
        do {
            second = new java.util.Random().nextInt(8);
        } while (second == 4);
        int r1 = 10000 + new java.util.Random().nextInt(90000);
        int r2 = 10000 + new java.util.Random().nextInt(90000);
        return title + "" + second + "" + r1 + "" + r2;
    }

    private static String getMac(){
        char[] char1 = "abcdef".toCharArray();
        char[] char2 = "0123456789".toCharArray();
        StringBuffer mBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int t = new java.util.Random().nextInt(char1.length);
            int y = new java.util.Random().nextInt(char2.length);
            int key = new java.util.Random().nextInt(2);
            if (key ==0) {
                mBuffer.append(char2[y]).append(char1[t]);
            }else {
                mBuffer.append(char1[t]).append(char2[y]);
            }

            if (i!=5) {
                mBuffer.append(":");
            }
        }
        return mBuffer.toString();
    }

    private static String getSDTotal(){

        int num = new java.util.Random().nextInt(12);
        int num1 =  new java.util.Random().nextInt(9999);
        int num2 =  new java.util.Random().nextInt(99999);
        String result = String.valueOf(num) + String.valueOf(num1) + String.valueOf(num2);
        return result;
    }

    private static String getIssid(){


        String result = "";
        for(int i = 0;i <4;i++){
            String num =  String.valueOf(new java.util.Random().nextInt(99999));
            if(num.length() < 5){
                for(int j = 0;j < 5;j++){
                    num += "0";
                }
            }
            result += num;
        }
        return result;
    }

    private static int getRandom1(){
        int num = new java.util.Random().nextInt(20000);
        return num;
    }

    private static int getRandom2(){
        int num = new java.util.Random().nextInt(99);
        return num;
    }

    public static JSONObject contrustTest(){



        JSONObject result = new JSONObject();
        String mac = getMac();
        try {
            result.put("imei","867246022383583");
            result.put("imsi","460021051968262");
            result.put("iccid","89860030101550063579");
            result.put("cpu","abi: armeabi-v7anProcessor\t: AArch64 Processor rev 2 (aarch64)\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\n\nHardware\t: MT6795T\nRevision\t: 5753\n");
            result.put("uid",10496);
            result.put("wifimac","68:3e:34:1e:c3:9a");
            result.put("type",0);
            result.put("idfa","ef8ad7929c2e0994");
            result.put("file","meta_init.rc,meta_init.project.rc,meta_init.modem.rc,init.xlog.rc,init.ssd.rc,init.project.rc,init.mt6795.usb.rc,init.mt6795.rc,init.mt6595.rc,init.modem.rc,init.environ.rc,init.aee.rc,fstab.mt6795,factory_init.rc,factory_init.project.rc,enableswap.sh,");
            result.put("fingerprint","Meizu/meizu_mx5/mx5:5.1/LMY47I/1517208287:user/release-keys");
            result.put("description","meizu_mx5-user 5.1 LMY47I 1517208287 release-keys");
            result.put("temperature",1);
            result.put( "mem",Long.parseLong("2860703744"));
            result.put("sdtotal",Long.parseLong("12660170752"));
            result.put("sdused",Long.parseLong("11610677248"));
            result.put( "battery",89);


            result.put("charge",0);
            result.put("os","5.1");
            result.put("display","1080,1920");
            result.put("root",0);
            result.put( "time",1536659702);
            result.put("active",199297);
            result.put("lock",5000);
            result.put("brand", "Meizu MX5");
            result.put("vpn",0);
            result.put("host",1);
            result.put("mac","68:3E:34:1E:C3:9A");
            result.put("cellid",0);
            result.put("provider", "中国移动");
            result.put("wifissid","\"1604穿透强\"");
            result.put("wifibssid", "dc:fe:18:62:f2:f5");
            result.put("h","");
            //result.put("h","eeef2000-eef42000 r--p 00000000 b3:14 499773                             /data/dalvik-cache/arm/system@framework@XposedBridge.jar@classes.dex\n");
            result.put("wifiip","192.168.0.155");
            result.put("sim",1);


            ArrayList<String> list1 = new ArrayList<String>();
            ArrayList<String> list2 = new ArrayList<String>();
            String []temp = {
                    "com.baidu.BaiduMap",
                    "com.eg.android.AlipayGphone",
                    "cn.edu.nju.iportal",
                    "com.taptap",
                    "com.lsj.dilidili",
                    "com.qiyi.video",
                    "com.tencent.mobileqq",
                    "com.baidu.searchbox",
                    "com.youku.phone.player.meizu",
                    "com.tencent.qqmusic",
                    "com.sankuai.meituan.takeoutnew",
                    "com.tencent.qqlive.player.meizu",
                    "com.shanbay.listen",
                    "com.xtuone.android.syllabus",
                    "com.baidu.netdisk",
                    "com.kingroot.kinguser",
                    "com.ss.android.ugc.aweme",
                    "com.yr.azj",
                    "tuoyan.com.xinghuo_daying",
                    "com.meizu.net.pedometer",
                    "com.tencent.mm",
                    "com.qq.ac.android",
                    "com.tencent.qqlive"
            };
            String [] temp1 = { "a95ba582302b804e4636d026f145e1cd",
                    "419ebf6adc1aeee06f8b3e8ce0d9178b",
                    "0afcd7b628f4c0e7573e7dfcfc325f02",
                    "20d2f1c97d4f6ce609b930627231c2c7",
                    "9edf3c656072a6104179699f0d5f2d77",
                    "a900b3fe745baa0f1f194354cc610700",
                    "4cdcf818f49bed8a639e38b942d3fcc7",
                    "5bbb0f1ae9dfd172e695397ee2f66fff",
                    "537f18241f96f82bfe857f6cbe10b2b8",
                    "35ebf91b8e778e5fbff5e4d491038df1"};
            for(int i = 0;i < temp.length;i++){
                list1.add(temp[i]);
            }
            for(int i = 0;i < temp1.length;i++){
                list2.add(temp1[i]);
            }

            result.put("applist",list1);
            result.put("apkcount","23");

            //result.put("photolist",list2);

            System.out.println(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

}
