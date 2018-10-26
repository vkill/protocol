package com.space.dyrev.util.deviceinfoutil;

import com.space.dyrev.request.commonparams.CommonParams;
import com.space.dyrev.util.formatutil.ScaleTrans;

import java.util.Random;
import java.util.UUID;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/15 23:32
 *                                                                                             @Description: 各种设备信息生成的util
 **/
public class CreateDevInfoUtil {


    /**
     * 生成uuid，格式 865166024287115
     * @return 15 位的uuid
     */
    public static String createUUID() {
        StringBuffer s1 = new StringBuffer("816");
        Random r1 = new Random();
        for (int i = 0; i < 12; i++) {
            s1.append(r1.nextInt(10));
        }
        return s1.toString();
    }

    /**
     * 生成openudid
     * @return cd5deef67704a09e的格式的字符串
     */
    public static String createOpenUdid() {
        StringBuffer sb = new StringBuffer("c");
        Random random = new Random();
        for (int i = 0; i< 15; i++) {
            sb.append(ScaleTrans.byteToChar((byte) random.nextInt(16)));
        }
        return sb.toString();
    }


    /**
     * 生成ClientUdid
     * @return 090be04d-475c-40ab-a229-f02f4b02214d
     */
    public static String createClientUdid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 设备类型
     * @return
     */
    public static  String createDeviceType() {
        Random random = new Random();
        return DeviceInfoSave.DEVICE_TYPE[random.nextInt(3)];
    }

    /**
     * 设备品牌
     * @return
     */
    public static String createDeviceBrand() {
        return DeviceInfoSave.DEVICE_BRAND[0];
    }

    /**
     * RESOLUTION
     * @return 1280*720
     */
    public static String createResolution() {
        return DeviceInfoSave.RESOLUTION[0];
    }

    /**
     * DPI
     * @return 320
     */
    public static String createDpi() {
        return DeviceInfoSave.DPI[0];
    }


    /**
     * buildSerial
     * @return 格式：6d16cfb7d440
     */
    public static String createBuildSerial() {
//        StringBuffer sb = new StringBuffer();
//        Random random = new Random();
//        for (int i = 0; i< 12; i++) {
//            if (i == 0) {
//                int j = random.nextInt(16);
//                while (j==0) {
//                    j = random.nextInt(16);
//                }
//                sb.append(ScaleTrans.byteToChar((byte) j));
//            }
//            sb.append(ScaleTrans.byteToChar((byte) random.nextInt(16)));
//        }
//        return sb.toString();

        return CommonParams.BUILD_SERIAL;
    }


    /**
     * 生成mac地址
     * @return F4:F5:DB:19:78:22
     */
    public static String createMac() {
        Random random = new Random();
        String[] mac = {
                String.format("%02x", 0xF4),
                String.format("%02x", 0xF5),
                String.format("%02x", 0xDB),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff))
        };
        return String.join(":", mac).toUpperCase();
    }

    /**
     * 生成imsi 4600 78015808988
     * @return
     */
    public static String createIMSI() {
        StringBuffer sb = new StringBuffer("4600");
        Random random = new Random();
        for (int i=0;i<11;i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }





    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(createIMSI());

        }
    }





}
