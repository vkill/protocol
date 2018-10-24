package com.space.dyrev.request.deviceregistermodule.params;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.enumeration.XlogEnum;
import com.space.dyrev.request.commomparams.CommonParams;

import java.util.HashMap;
import java.util.Map;

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
 *        @Author: space
 *        @Date: 2018/10/17 14:31
 *        @Description: xlog请求的参数
 **/
public class XlogV2Params {


    private static final String VER = "0.5.8.28";

    private static final String HOST = "xlog.snssdk.com";

    private static final String FUNC = "v2/r";

    /**
     * 构造该请求的header
     * @param deviceEntity
     * @return
     */
    public static Map constructHeader(DeviceEntity deviceEntity) {
        Map result = new HashMap();
        result.put("Host", HOST);
        result.put("Connection", "Keep-Alive");
        result.put("Accept-Encoding", "gzip");
        result.put("User-Agent", "okhttp/3.10.0.1");
        result.put("Content-length", "1155");
//        result.put("Content-Type", "application/octet-stream");
        result.put("Cookie","session=");
        return result;
    }

    /**
     * 构造url
     * @param deviceEntity
     * @param xlogEnum
     * @return
     */
    public static String constructV2Url(DeviceEntity deviceEntity, XlogEnum xlogEnum) {
        // https://xlog.snssdk.com/v2/r?os=0&ver=0.5.8.28&m=1&app_ver=2.7.0&region=CN&aid=1128&did=
        // https://xlog.snssdk.com/v2/r?os=0&ver=0.5.8.28&m=1&app_ver=2.7.0&region=CN&aid=1128&did=57616910195
        StringBuffer sb = new StringBuffer();
        sb.append("https://"+ HOST + "/" + FUNC + "?");
        sb.append("os=0");
        sb.append("&ver=" + VER);
        sb.append("&app_ver=" + CommonParams.VERSION_NAME);
        sb.append("&m=1");
        sb.append("&region=CN");
        sb.append("&aid=" + CommonParams.AID);
        sb.append("&did=");
        if (XlogEnum.LOGIN == xlogEnum) {
            sb.append(deviceEntity.getDeviceId());
        }
        return sb.toString();
    }


    /**
     * 生成xlog.snssdk.com/v2/r请求的json
     * @param deviceEntity 设备信息
     * @return 返回json
     */
    public static JSONObject constructV2Json(DeviceEntity deviceEntity, XlogEnum xlogEnum) {
        JSONObject result = new JSONObject();
        JSONObject hw = new JSONObject();
        JSONObject cpu = new JSONObject();
        JSONObject mem = new JSONObject();

        JSONObject id = new JSONObject();

        JSONObject emulator = new JSONObject();

        JSONObject env = new JSONObject();

        JSONObject extension = new JSONObject();


        cpu.put("hw", "Qualcomm Technologies, Inc MSM8940");
        cpu.put("max", "1401000");
        cpu.put("min", "960000");
        cpu.put("ft", "half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt lpae evtstrm aes pmull sha1 sha2 crc32");

        mem.put("ram", "2.77 GB");
        mem.put("rom", "24.08 GB");
        mem.put("sd", "24.08 GB");

        hw.put("brand", deviceEntity.getDeviceBrand());
        hw.put("model", deviceEntity.getDeviceType());
        hw.put("board", "QC_Reference_Phone");
        hw.put("device", "santoni");
        hw.put("product", "santoni");
        hw.put("bt", "unknown");
        hw.put("display", deviceEntity.getResolution());
        hw.put("dpi", Integer.parseInt(deviceEntity.getDpi()));
        hw.put("bat", 4100);
        hw.put("cpu", cpu);
        hw.put("mem", mem);

        id.put("imei", deviceEntity.getImei());
        id.put("imsi", deviceEntity.getImsi());
        id.put("adid", deviceEntity.getAdid());
        id.put("mac", deviceEntity.getMc().toLowerCase());
        id.put("serial", deviceEntity.getBuildSerial());

        emulator.put("sig", 0);
        emulator.put("cb", 10);
        emulator.put("file", new JSONArray());
        emulator.put("prop", new JSONArray());

        env.put("ver", VER);
        env.put("pkg", CommonParams.PACKAGE);
        env.put("uid", 10206); // 不知道是什么
        env.put("rebuild", 0);
        env.put("jd", 0);
        env.put("dbg", 0);
        env.put("tid", 0);
        env.put("xpd", 0);
        env.put("hk", new JSONArray());
        env.put("su", 0); // 是否超级权限？


        env.put("sp", "\\/system\\/xbin\\/su");
//        env.put("sp", "/system/xbin/su");
        env.put("ro.secure_s", "");
        env.put("ro.debuggable_s", "");
        env.put("click", "");
        env.put("hph", "192.168.0.108"); // 代理信息
        env.put("hpp", "8888"); // 监听？？？
        env.put("mc", 0);
        env.put("fc", 57580);
        env.put("jexp", 0);
        env.put("xposed", 0);
        env.put("cydia", 0);
        env.put("frida", 0);
        env.put("vapp", "");
        env.put("api", new JSONArray());

        // notify不确定是否有关系，先写死
        extension.put("notify", 1258375024);
        extension.put("tpid1", 0);
        extension.put("tbin", "");


        result.put("pkg", CommonParams.PACKAGE);
        result.put("fp", "Xiaomi\\/santoni\\/santoni:7.1.2\\/N2G47H\\/8.9.13:user\\/release-keys");
//        result.put("fp", "Xiaomi/santoni/santoni:7.1.2/N2G47H/8.9.13:user/release-keys");
        result.put("vc", Integer.parseInt(CommonParams.VERSION_CODE));
        result.put("VPN", 0);
        result.put("wifimac", deviceEntity.getMc().toLowerCase());
        result.put("location", "");
        result.put("apps", new JSONArray());
        result.put("hw", hw);
        result.put("id", id);
        result.put("emulator", emulator);
        result.put("env", env);
        if (xlogEnum == XlogEnum.LOGIN) {
            result.put("p1", deviceEntity.getDeviceId());
            result.put("p2", deviceEntity.getInstallId());
            result.put("extra", xlogEnum.getState());
        }
        if (xlogEnum == XlogEnum.COLD_START) {
            result.put("p1", "");
            result.put("p2", "");
            result.put("extra", xlogEnum.getState());
        }
        result.put("extension", extension);
        return result;
    }
}
