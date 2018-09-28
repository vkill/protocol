package com.space.register.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.space.register.dao.DYUserRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;


import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;

import com.space.register.service.UrlRequestService;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    @Resource
    UrlRequestService urlRequestService;

    @Resource
    DeviceService deviceService;

    @Resource
    DYRegisterService dyRegisterService;

    @Resource
    DYUserRepository dyUserRepository;

    private static int id = 748;
    private static int event_id = 0;
    private static String session_id = "";
    private static long serverTime = 0;
    /**
     * 点赞模块
     * @return
     */
    @RequestMapping("/thumbsUp")
    public String thumbsUpMaker() {

        //这个是视频id，需要参数传入
        //String aweme_id = "6597344642847477000";
        //String aweme_id = "6596873554115955971";
        String aweme_id = "6602797333635665156";
        //String aweme_id = "6599566234746883335";
//        String aweme_id = "6599758421971438862";
        //String aweme_id = "6598666622192323844";

//        ArrayList<String> aaaa = new ArrayList<String>();
//        aaaa.add("6601079867616267528");
//        aaaa.add("6602390827073277197");
//        aaaa.add("6601800939487628558");
//        aaaa.add("6599566234746883335");
//        aaaa.add("6601500048515665166");

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);

        long time = System.currentTimeMillis();
        OkHttpClient okHttpClient = new OkHttpClient();
        ArrayList<String> result = ThumbsUpMaker.thumbsUpMaker(okHttpClient, aweme_id, deviceEntity, dyUserEntity);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        String like = "{\"request_id\":\""+request_id+"\",\"order\":0,\"enter_from\":\"homepage_hot\",\"enter_method\":\"click\",\"nt\":4,\"category\":\"umeng\",\"tag\":\"like\",\"label\":\"homepage_hot\",\"value\":"+aweme_id+",\"user_id\":"+user_id+",\"session_id\":\""+session_id+"\",\"datetime\":\""+sdf.format(Long.parseLong(digg_time))+"\",\"event_id\":"+(++event_id)+"}";

        ArrayList<String> body_msg = AllAppLogConstruct.digg(dyUserEntity.getAppLog(), session_id, event_id, String.valueOf(serverTime), String.valueOf(time), dyUserEntity.getUid(), aweme_id, result.get(1));

        event_id = Integer.valueOf(body_msg.get(0));
        System.out.println("点赞的applog：");
        String appLogResult = AppLogMaker.app_log(okHttpClient, deviceEntity, dyUserEntity, body_msg.get(1));
        System.out.println(appLogResult);
        dyUserEntity.setEvent_id(event_id);
        dyUserRepository.save(dyUserEntity);

        return result.get(0);
    }

    @RequestMapping("/follow")
    public String followMaker(){

        //这个是视频id，需要参数传入
     //   String user_id = "101947485841";
        String user_id = "59024460599";
        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));

        long time = System.currentTimeMillis();
        OkHttpClient okHttpClient = new OkHttpClient();
        ArrayList<String> follow_result = FollowMaker.FollowMaker(okHttpClient, user_id, dyUserEntity, deviceEntity);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = SupportAccountMaker.getAwemeId(okHttpClient, deviceEntity, dyUserEntity, user_id);

        ArrayList<String> body_msg = AllAppLogConstruct.follow(dyUserEntity.getAppLog(), session_id, event_id, String.valueOf(serverTime), String.valueOf(time), user_id, result, dyUserEntity.getUid(),follow_result.get(2));;

        event_id = Integer.valueOf(body_msg.get(0));
        System.out.println("关注的applog:");
        String appLogResult = AppLogMaker.app_log(okHttpClient, deviceEntity, dyUserEntity, body_msg.get(1));
        System.out.println(appLogResult);
        dyUserEntity.setEvent_id(event_id);
        dyUserRepository.save(dyUserEntity);

        return null;
    }

    @RequestMapping("/modify")
    public String modificationMaker(){

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();
        String uid = dyUserEntity.getUid();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity1 =urlRequestService.getUrlRequest(5);


        UrlRequestEntity urlRequestEntity2 = urlRequestService.getUrlRequest(8);

        OkHttpClient okHttpClient = new OkHttpClient();
        ModifyInfoMaker.modifyInfoMaker(okHttpClient, uid, deviceEntity, urlRequestEntity1, urlRequestEntity2);


        return null;
    }

    @RequestMapping("/support")
    public String supportAccountMaker(){

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(6);

        OkHttpClient okHttpClient = new OkHttpClient();
        ArrayList<String> awemeList =  SupportAccountMaker.getAwemeListMaker(okHttpClient, deviceEntity, urlRequestEntity);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        awemeList.add("6602390827073277197");
        UrlRequestEntity urlRequestEntity1 = urlRequestService.getUrlRequest(7);

        try {
            for(int i = 0;i < awemeList.size();i++){
                SupportAccountMaker.getVideoMaker(okHttpClient, awemeList.get(i),deviceEntity, urlRequestEntity1);
                Thread.sleep(2000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/launchApp")
    public String launchApp() throws InterruptedException {

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));

        //随机生成的session_id
        session_id = AllAppLogConstruct.constructRandomSessionId();
        ArrayList<String> body_msg = AllAppLogConstruct.launchApp(dyUserEntity.getAppLog(), session_id, dyUserEntity.getEvent_id());
        //修改全部变量event_id
        event_id = Integer.valueOf(body_msg.get(0));
        //修改数据库中event_id的值
        dyUserEntity.setEvent_id(event_id);
        dyUserRepository.save(dyUserEntity);
        //修改全局变量serverTime
        serverTime = System.currentTimeMillis();

        OkHttpClient okHttpClient = new OkHttpClient();
        String result = AppLogMaker.app_log(okHttpClient, deviceEntity, dyUserEntity, body_msg.get(1));
        System.out.println(result);
        return null;
    }


    @RequestMapping("/test")
    public void testStart() throws InterruptedException {


//        ArrayList<DYUserEntity> dyUserEntity_list = dyRegisterService.findAll();
//        int site = 0;
//        for(int i = 0;i < dyUserEntity_list.size();i++){
//            if(dyUserEntity_list.get(i).getId() == 746){
//                site = i;
//            }
//        }
//
//        for(int i = site;i < dyUserEntity_list.size();i++){
//            id = dyUserEntity_list.get(i).getId();
//            System.out.println(id);
//            launchApp();
//            Thread.sleep(1000);
            thumbsUpMaker();
//            Thread.sleep(1000);
////            followMaker();
////            Thread.sleep(1000);
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("----------------------烧楼上屁股----------------------");
//            System.out.println("-----------------------------------------------------");
//        }


        //下面是单个id循环测试
//        //通过id获取t_dy_user中的数据
//        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
//        String user_cookie = dyUserEntity.getUserCookie();
//        String simulationId = dyUserEntity.getSimulationID();
//
//        //通过simulationid获取t_device中的数据
//        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
//        String cookie = deviceEntity.getCookie();
//        cookie += (";"+ user_cookie);
//        deviceEntity.setCookie(cookie);
//
//        //获取并构建url信息，包括host、msg、token
//        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(6);
//
//        ArrayList<String> result = new ArrayList<>();
//        for(int i = 0;i < 10;i ++){
//            ArrayList<String> temp = SupportAccountMaker.getAwemeListMaker(deviceEntity, urlRequestEntity);
//            System.out.println("取到视频id");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            for(int j = 0;j < temp.size();j++){
////                result.add(temp.get(j));
////            }
//            for(int j = 0;j < temp.size();j ++){
//                thumbsUpMaker(temp.get(j));
//                Thread.sleep(500);
//            }
//        }
        //ArrayList<String> awemeList =  SupportAccountMaker.getAwemeListMaker(deviceEntity, urlRequestEntity);





    }

    public static String MapToString(Map map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? ";" : "");
        }
        return sb.toString();
    }
}
