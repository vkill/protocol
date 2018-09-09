package params.tools;

import com.space.register.entity.DeviceEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @program: register
 * @description: 构造deviceInfo的json串
 * @author: Mr.Jia
 * @create: 2018-09-09 15:04
 **/
public class ContrustDeviceInfo {

    private static String SEPARATOR_OF_MAC = ":";

    public static void main(String[] args) {

        System.out.println(getIssid());
    }

    public static String randomMac4Qemu() {
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

    public static JSONObject contrust(DeviceEntity deviceEntity){



        JSONObject result = new JSONObject();
        String mac = getMac();
        try {
            result.put("imei",deviceEntity.getUuid());
            result.put("imsi",getImsi());
            result.put("iccid",getIssid());
            result.put("cpu","abi: armeabi-v7anProcessor\t: AArch64 Processor rev 2 (aarch64)\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\nBogoMIPS\t: 26.00\nFeatures\t: fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architecture: 8\nCPU variant\t: 0x0\nCPU part\t: 0xd03\nCPU revision\t: 2\n\nHardware\t: MT6795T\nRevision\t: 5753\n");
            result.put("uid",getRandom1());
            result.put("wifimac",mac);
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
            result.put( "time",String.valueOf(System.currentTimeMillis()));
            result.put("active",getRandom1());
            result.put("lock",5000);
            result.put("brand", "Meizu MX5");
            result.put("vpn",0);
            result.put("host",1);
            result.put("mac",mac.toUpperCase());
            result.put("cellid",0);
            result.put("provider", "中国移动");
            result.put("wifissid","1604穿透强");
            result.put("wifibssid", getMac());
            result.put("h","");
            //result.put("h","eeef2000-eef42000 r--p 00000000 b3:14 499773                             /data/dalvik-cache/arm/system@framework@XposedBridge.jar@classes.dex\n");
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

            System.out.println(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getImsi() {
        String title = "5200";
        int second = 0;
        do {
            second = new java.util.Random().nextInt(8);
        } while (second == 4);
        int r1 = 10000 + new java.util.Random().nextInt(90000);
        int r2 = 10000 + new java.util.Random().nextInt(90000);
        return title + "" + second + "" + r1 + "" + r2;
    }

    public static String getMac(){
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

    public static String getSDTotal(){

        int num = new java.util.Random().nextInt(12);
        int num1 =  new java.util.Random().nextInt(9999);
        int num2 =  new java.util.Random().nextInt(99999);
        String result = String.valueOf(num) + String.valueOf(num1) + String.valueOf(num2);
        return result;
    }

    public static String getIssid(){


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

    public static int getRandom1(){
       int num = new java.util.Random().nextInt(20000);
       return num;
    }

    public static int getRandom2(){
        int num = new java.util.Random().nextInt(99);
        return num;
    }
}
