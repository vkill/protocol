package com.space.register.controller;

import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DeviceService;
import enums.paramtable.DirTable;
import enums.paramtable.urltools.URLmakeTools;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import jsonreader.tools.JsonTableGetter;
import keytools.Crc32;
import keytools.ScretAES;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.ParamCreater;
import params.tools.RequestURLCreater;
import platform.email.EmailGetter;
import platform.email.HostIPGetter;
import platform.main.*;
import platform.thread.RegisterThread;
import po.HostIPPo;
import po.PhonePo;
import po.RequestTokenVo;

import javax.annotation.Resource;
import javax.management.Query;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    @Resource
    DeviceRepository deviceRepository;

    @Resource
    DYUserRepository dyUserRepository;
    public static List<UrlRequestEntity> allUrl;
    public static int thread_num = 5;
    public static LinkedBlockingQueue<HostIPPo> hostIpQuene = new LinkedBlockingQueue<HostIPPo>();
    public static Vector<DeviceEntity> deviceEntities = new Vector<DeviceEntity>();
    public static Vector<DYUserEntity> userEntities = new Vector<DYUserEntity>();
    @RequestMapping("/maker")
    public String deviceMain(){
        int number = 1;
        //getNeedIPFromWeb();
        Thread[] registerThreads =new Thread[number];
        for(int i=0;i<number;i++){
            registerThreads[i] = new Thread(new RegisterThread());
            registerThreads[i].start();
        }

        return "呵呵哒哒";
    }

    public static void getNeedIPFromWeb(){
        ArrayList<HostIPPo> hostIPPos = HostIPGetter.getIpByXdali(HostIPGetter.count);
        for(HostIPPo hostIPPo:hostIPPos){
            if(!hostIpQuene.offer(hostIPPo)){
                System.out.println("队列都插不进去 你敢信？？");
            }
        }
        if(hostIpQuene.size()<HostIPGetter.count){
            getNeedIPFromWeb();
        }
        return;
    }
}
//    ArrayList<DYUserEntity> dyUserEntities1 = dyUserRepository.findAll();
//    ArrayList<DYUserEntity> dyUserEntities = new ArrayList<>();
//        System.out.println(dyUserEntities.size());
//                for(DYUserEntity dyUserEntity:dyUserEntities1){
//                if(dyUserEntity.getId()>=143){
//                dyUserEntities.add(dyUserEntity);
//                }
//                }
//                System.out.println(dyUserEntities.size());
//                try {
//                Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                e.printStackTrace();
//                }
//                DeviceEntity deviceEntity = null;
//                DYUserEntity dyUserEntity =null;
//                int count =0;
//                int num = 1;
//                while (num<dyUserEntities.size()) {
//        HostIPPo hostIPPo = HostIPGetter.getIpByXdali(1).get(0);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//        .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
//        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
//        .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
//        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostIPPo.host, hostIPPo.port)))
//        .build();
//        count++;
//        System.out.println("用掉IP数量为：" + count);
//        for (int i = 0; i < 10; i++) {
//        dyUserEntity = dyUserEntities.get(num);
//        num++;
//        if(num>dyUserEntities.size()){
//        break;
//        }
//        deviceEntity = deviceRepository.getOne(Integer.parseInt(dyUserEntity.getSimulationID()));
//        Request request = UserPowerGetter.getAppLogs(dyUserEntity, deviceEntity);
//        try {
//        Response response = okHttpClient.newCall(request).execute();
//        System.out.println(GzipGetteer.uncompressToString(response.body().bytes()));
//        dyUserEntity.setLikePower(0);
//        dyUserRepository.save(dyUserEntity);
//        } catch (IOException e) {
//        e.printStackTrace();
//        break;
//        }
//        System.out.println(deviceEntity.getId() + " 号被解封");
//
//        }
//
//        }