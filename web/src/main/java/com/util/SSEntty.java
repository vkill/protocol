package com.util;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: protool
 * @description: 某平台简单的entty模拟算法
 * @author: Mr.gao
 * @create: 2018-09-25 20:31
 **/
public class SSEntty {
    //加密对照表
    public static int[] byteTable;
    //默认加密字段内容
    public static String r1 = "fde58f8f";

    public static String r2 = "fdcf434d";

    public static String r3 = "33ef9d50";

    public static String r4 = "3f929618";

    public static String all = r1+r2+r3+r4;
    static{
        byteTable = getBytesFromString();
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

    //从IDA的内存存储里提取数字的方法：
    public static int[] getBytesFromString(){
        //想要使用正则表达式，请手动在末尾添加\n
        String nCString = "DCB 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30\n" +
                ".data:00004110                                         ; DATA XREF: LOAD:0000025C↑o\n" +
                ".data:00004110                                         ; ss_encrypt+68↑o ...\n" +
                ".data:00004110                 DCB 1, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 0xCA, 0x82\n" +
                ".data:00004110                 DCB 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2\n" +
                ".data:00004110                 DCB 0xAF, 0x9C, 0xA4, 0x72, 0xC0, 0xB7, 0xFD, 0x93, 0x26\n" +
                ".data:00004110                 DCB 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71\n" +
                ".data:00004110                 DCB 0xD8, 0x31, 0x15, 4, 0xC7, 0x23, 0xC3, 0x18, 0x96\n" +
                ".data:00004110                 DCB 5, 0x9A, 7, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75\n" +
                ".data:00004110                 DCB 9, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52\n" +
                ".data:00004110                 DCB 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1\n" +
                ".data:00004110                 DCB 0, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE\n" +
                ".data:00004110                 DCB 0x39, 0x4A, 0x4C, 0x58, 0xCF, 0xD0, 0xEF, 0xAA, 0xFB\n" +
                ".data:00004110                 DCB 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 2, 0x7F, 0x50\n" +
                ".data:00004110                 DCB 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D\n" +
                ".data:00004110                 DCB 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3\n" +
                ".data:00004110                 DCB 0xD2, 0xCD, 0xC, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17\n" +
                ".data:00004110                 DCB 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73, 0x60\n" +
                ".data:00004110                 DCB 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE\n" +
                ".data:00004110                 DCB 0xB8, 0x14, 0xDE, 0x5E, 0xB, 0xDB, 0xE0, 0x32, 0x3A\n" +
                ".data:00004110                 DCB 0xA, 0x49, 6, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91\n" +
                ".data:00004110                 DCB 0x95, 0xE4, 0x79, 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5\n" +
                ".data:00004110                 DCB 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE\n" +
                ".data:00004110                 DCB 8, 0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6\n" +
                ".data:00004110                 DCB 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A, 0x70\n" +
                ".data:00004110                 DCB 0x3E, 0xB5, 0x66, 0x48, 3, 0xF6, 0xE, 0x61, 0x35, 0x57\n" +
                ".data:00004110                 DCB 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98, 0x11\n" +
                ".data:00004110                 DCB 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE\n" +
                ".data:00004110                 DCB 0x55, 0x28, 0xDF, 0x8C, 0xA1, 0x89, 0xD, 0xBF, 0xE6\n" +
                ".data:00004110                 DCB 0x42, 0x68, 0x41, 0x99, 0x2D, 0xF, 0xB0, 0x54, 0xBB\n" +
                ".data:00004110                 DCB 0x16\n";
        String rgex = "DCB(.*?)\n";
        List<String> allPattern = getSubUtil(nCString,rgex);
        ArrayList<Integer> integers = new ArrayList<>();
        for(String nums:allPattern){
            String[] intNums = nums.split(",");
            for(int i =0;i<intNums.length;i++){
                if(intNums[i].length()>2){
                    integers.add(Integer.parseInt(intNums[i].substring(3).trim(),16));
                }
                else{
                    integers.add(Integer.parseInt(intNums[i].trim()));
                }
            }
        }
        int[] resultByte = new int[integers.size()];
        int hehe =0;
        for(Integer num:integers){
            resultByte[hehe] = num;
            hehe++;
        }
        return resultByte;
    }

    public static byte[] getTTEnttyResult(byte[] ttEnttyString){
        int num = ttEnttyString.length/16;
        if(ttEnttyString.length%16!=0){
            num++;
        }
        int[] enttyString = new int[16 * num];
        for(int i = 0;i<enttyString.length;i++){
            if(i<ttEnttyString.length){
                enttyString[i] = ttEnttyString[i];
            }
            else{
                enttyString[i] = 32;
            }
        }
        int[] newBytes = new int[enttyString.length];
        for(int i = 0;i<newBytes.length;i++){
            newBytes[i] = byteTable[enttyString[i]&0xff];
        }
        return getFinallResult(newBytes);
    }

    public static byte[] getFinallResult(int[] tableEntty){
        if(tableEntty.length%16!=0){
            System.out.println("数据格式不是十六的倍数，不符合要求");
            return null;
        }
        else {
            byte[] result = new byte[tableEntty.length+4];
            result[0] = 116;
            result[1] = 99;
            result[2] = 2;
            result[3] = 0;
            for(int z=0;z*16<tableEntty.length;z++){
                int[] myself = new int[16];
                for(int k =0;k<16;k++){
                    myself[k] = tableEntty[z*16+k];
                }
                byte[] kao = dofinalEntty(myself);
                for(int i=0;i<16;i++){
                    result[z*16+i+4] = kao[i];
                }
            }
            return result;
        }
    }

    public static byte[] dofinalEntty(int[] waitString){
        if(waitString.length!=16){
            System.out.println("加密的数组长度不为16");
            return null;
        }
        //很煞笔，但是不得不这样写
        int[] resulrString = new int[waitString.length];
        int key=-1;
        int buff;
        for(int i =0;i<waitString.length;i++){
            if(i%4==0){
                key++;
            }
            int z = i-key;
            if(z<4*key){
                z+=4;
            }else{

            }
            resulrString[z] = waitString[i];
        }
        for(int i =0;i<resulrString.length;i++){
            System.out.print(resulrString[i]+" ");
        }
        System.out.println();
        byte[] resultByte = new byte[resulrString.length];
        for(int i= 0;i<resulrString.length;i++){
            resultByte[i]=xor(resulrString[i],all.substring(2*i,2*i+2));
        }
        return  resultByte;
    }

    private static byte xor(int strHex_X,String strHex_Y){
        //将x、y转成二进制形式
        String anotherBinary=Integer.toBinaryString(strHex_X);
        String thisBinary=Integer.toBinaryString(Integer.valueOf(strHex_Y,16));
        String result = "";
        //判断是否为8位二进制，否则左补零
        if(anotherBinary.length() != 8){
            for (int i = anotherBinary.length(); i <8; i++) {
                anotherBinary = "0"+anotherBinary;
            }
        }
        if(thisBinary.length() != 8){
            for (int i = thisBinary.length(); i <8; i++) {
                thisBinary = "0"+thisBinary;
            }
        }
        //异或运算
        for (int i=0;i<anotherBinary.length();i++){
            //如果相同位置数相同，则补0，否则补1
            if(thisBinary.charAt(i)==anotherBinary.charAt(i))
                result+="0";
            else{
                result+="1";
            }
        }
        return (byte)(Integer.parseInt(result, 2)&(0xFF));
    }
    public static void main(String[]args){
        String str = "{\"event\":[{\"nt\":4,\"category\":\"umeng\",\"tag\":\"monitor\",\"label\":\"terminate\",\"session_id\":\"3482178f-0bab-41d9-a709-eafe55b66595\",\"datetime\":\"2018-09-26 21:49:27\",\"event_id\":144},{\"nt\":4,\"category\":\"umeng\",\"tag\":\"monitor\",\"label\":\"launch\",\"session_id\":\"3482178f-0bab-41d9-a709-eafe55b66595\",\"datetime\":\"2018-09-26 21:49:27\",\"event_id\":145},{\"nt\":4,\"category\":\"umeng\",\"tag\":\"notice\",\"label\":\"allow_on\",\"session_id\":\"3482178f-0bab-41d9-a709-eafe55b66595\",\"datetime\":\"2018-09-26 21:49:27\",\"event_id\":146},{\"nt\":4,\"category\":\"umeng\",\"tag\":\"launch_app\",\"label\":\"enter_launch\",\"session_id\":\"3482178f-0bab-41d9-a709-eafe55b66595\",\"datetime\":\"2018-09-26 21:49:27\",\"event_id\":147}],\"launch\":[{\"datetime\":\"2018-09-26 21:49:27\",\"session_id\":\"3482178f-0bab-41d9-a709-eafe55b66595\",\"is_background\":true},{\"datetime\":\"2018-09-26 21:49:28\",\"session_id\":\"bf359e8a-1bbf-4a57-b700-922a31b6a44b\"}],\"magic_tag\":\"ss_app_log\",\"time_sync\":{\"server_time\":1537969767,\"local_time\":1537969767},\"header\":{\"appkey\":\"57bfa27c67e58e7d920028d3\",\"udid\":\"866709033510131\",\"openudid\":\"cd5deef67704a09e\",\"sdk_version\":201,\"package\":\"com.ss.android.ugc.aweme\",\"channel\":\"tengxun\",\"display_name\":\"抖音短视频\",\"app_version\":\"1.7.6\",\"version_code\":176,\"timezone\":8,\"access\":\"wifi\",\"os\":\"Android\",\"os_version\":\"7.1.2\",\"os_api\":25,\"device_model\":\"Redmi 4X\",\"device_brand\":\"Xiaomi\",\"device_manufacturer\":\"Xiaomi\",\"language\":\"zh\",\"resolution\":\"1280x720\",\"display_density\":\"xhdpi\",\"density_dpi\":320,\"mc\":\"F4:F5:DB:19:78:22\",\"carrier\":\"中国移动\",\"mcc_mnc\":\"46000\",\"clientudid\":\"6498fc88-2815-426a-8a9b-1a8b567fa29f\",\"device_id\":\"57616910195\",\"sig_hash\":\"aea615ab910015038f73c47e45d21466\",\"aid\":1128,\"push_sdk\":[1,2,6,7,8,9],\"rom\":\"MIUI-8.9.13\",\"release_build\":\"67a6344_20180308\",\"update_version_code\":1762,\"manifest_version_code\":176,\"cpu_abi\":\"armeabi-v7a\",\"build_serial\":\"6d16cfb7d440\",\"serial_number\":\"6d16cfb7d440\",\"sim_serial_number\":[{\"sim_serial_number\":\"898600b0101580028426\"}],\"not_request_sender\":0,\"rom_version\":\"miui_V10_8.9.13\",\"region\":\"CN\",\"tz_name\":\"Asia\\/Shanghai\",\"tz_offset\":28800000,\"sim_region\":\"cn\"},\"_gen_time\":1537969768416}";
        byte[] compress = GzipGetteer.compress(str);
        // 31 -117 8 0 0 0 0 0 0 0 -67 85 61 -113 -36 54 16 -3 43 6 107 73 33 37 -118 -92 -44 57 9 12 -72 72 -118 4 9 12 36 1 49 -94 40 45 113 18 -75 -47 -57 125 -30 -38 20 -18 -36 -92 73 97 32 -115 -45 -72 114 99 32 -56 -65 -119 -29 -8 95 100 40 -99 -17 22 119 65 98 -72 -72 109 118 57 -61 -103 121 124 51 111 -10 -126 -40 99 -21 103 82 126 119 65 -62 23 -113 -120 -127 -39 -74 -61 120 70 74 -78 -12 -42 -73 36 34 51 -76 120 -22 7 -17 -26 97 -60 115 7 -107 -19 -48 50 -37 -79 119 30 -17 -93 109 -78 -45 -28 6 -81 93 -115 -114 -116 -85 -108 73 -43 -60 -76 -126 42 -26 -84 46 98 -112 -76 -120 45 52 54 -49 43 33 -14 34 -57 -104 26 67 103 -41 91 -116 72 41 83 49 -34 72 -59 -125 -108 -107 -68 40 83 -119 23 86 112 107 74 -58 -7 101 -12 49 24 59 88 -68 -39 -35 7 -64 -4 67 0 -6 97 118 -58 30 -32 -125 -82 27 78 -12 -32 -17 3 -95 -8 16 -124 27 97 26 -10 -5 3 -108 -104 -61 -114 -6 -2 -72 -108 -105 63 68 -17 91 23 102 -13 127 -125 63 6 -111 -101 116 5 -26 -88 29 -121 -59 99 -40 60 46 54 16 -12 -97 -75 -44 -19 90 85 -109 -27 -123 85 16 -77 -86 106 98 14 -71 -116 43 73 105 92 -92 41 100 -84 18 -64 121 69 -62 107 122 104 -99 -47 27 -57 -45 20 -8 -43 -35 -80 -46 -114 -75 -12 116 -26 13 41 47 48 -9 120 -116 76 111 -11 89 -98 -55 66 20 82 72 36 99 48 -48 -35 -79 95 70 100 103 -95 -74 99 -120 -59 -108 71 54 52 52 -105 85 3 -87 52 66 -38 92 89 89 23 41 -91 -87 -86 51 44 -74 -44 43 106 37 4 18 66 -77 44 103 -108 101 12 29 -61 -34 -6 43 -89 -87 -13 -38 -38 70 72 73 57 -48 98 85 119 125 -92 17 87 120 54 41 -111 -107 -120 -20 -111 57 104 3 73 102 -24 -109 105 74 -64 -41 -29 -32 -22 100 105 77 2 39 -74 15 97 102 7 -34 95 -83 10 -33 -98 46 97 -54 107 55 -19 59 56 -45 30 86 -118 -1 122 -6 -13 -69 -25 -81 -34 62 127 -7 -9 -117 -97 -34 -3 -6 12 47 4 102 -82 107 17 -106 -56 68 -96 -11 -54 -94 -51 80 7 6 -92 -40 -120 59 31 60 30 21 70 25 -125 109 -63 -128 19 -41 -72 -16 -98 -16 -5 -31 -122 105 61 30 -28 -108 9 75 -46 -51 8 123 -121 15 -54 17 -106 61 70 97 -22 30 -45 7 -68 95 -39 -70 119 15 -8 19 114 -19 -87 70 8 83 66 -98 56 24 122 119 99 -17 -63 47 13 -104 121 25 67 23 110 -36 29 -8 118 -39 24 58 15 -102 25 -19 52 116 -53 124 -11 -86 84 -47 83 -103 -46 3 62 106 -21 39 55 -121 -10 -99 -18 -22 -3 86 96 -75 -24 112 42 -77 -108 -30 12 -31 -116 -112 71 -68 124 -108 -105 -97 127 90 -78 -94 -108 -86 76 -61 75 12 -116 -93 91 -21 -1 -7 -6 -27 -101 95 -2 120 -5 -30 -9 55 79 127 35 33 -62 -24 62 -116 22 -31 -126 -46 80 -49 116 14 117 118 -43 106 -63 11 -43 24 -91 -30 84 -79 60 -26 -87 -128 88 65 81 -59 12 84 -107 11 -119 83 84 52 55 79 93 67 114 41 -104 40 112 108 86 13 77 -82 -43 59 -104 118 97 -109 89 16 44 -121 10 125 -108 -27 52 83 -115 -52 12 -105 -106 -25 117 -118 -69 39 52 17 86 117 -29 -29 113 126 -106 105 -89 113 -84 80 -34 44 74 35 17 -55 72 69 5 -22 100 28 122 76 -10 -59 -29 111 30 -57 42 41 18 -106 -83 -36 117 22 38 -20 -63 -30 -70 21 -74 4 -111 113 -82 -125 62 105 70 -125 40 -105 125 -48 -83 -66 51 38 105 80 -98 119 -115 -99 -26 -69 78 100 99 -65 104 -88 92 -128 63 -10 22 127 -59 -57 18 48 -35 90 73 -93 26 29 -124 113 16 53 19 -90 -87 100 -51 57 93 55 64 -80 107 -65 -12 -43 74 -6 109 -73 -21 -11 -83 43 -72 -61 -2 -59 74 84 -95 -80 45 21 69 58 115 21 68 -118 45 88 -73 5 -2 79 -24 -47 -2 -72 4 -40 -109 -11 -85 -62 -23 74 -50 -63 28 -9 110 113 -6 91 70 -11 1 81 -19 -26 -6 -20 -53 -80 89 -50 -33 -85 -20 -31 -28 -32 -5 79 -66 70 57 -74 59 112 -101 107 104 -102 -55 -30 -97 65 -86 -80 50 126 54 -36 -41 25 -116 39 -72 93 116 107 -3 -19 -83 -93 56 19 -105 -1 0 103 -14 -97 72 58 8 0 0
        for (int i=0;i<compress.length;i++) {
            System.out.print(" "+compress[i]);
        }


        byte[] result = getTTEnttyResult(compress);
        for (int i=0;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
    }
}