package com.space.register.controller;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;
import com.space.register.service.UrlRequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.FollowMaker;
import params.SupportAccountMaker;
import params.ModifyInfoMaker;
import params.ThumbsUpMaker;

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

    /**
     * 点赞模块
     * @return
     */
    @RequestMapping("/thumbsUp")
    public String thumbsUpMaker() {

        //这个是视频id，需要参数传入
        String aweme_id = "6597344642847477000";

        //ArrayList<DYUserEntity> dyUserEntity_list = dyRegisterService.findAll();


            //通过id获取t_dy_user中的数据
            DYUserEntity dyUserEntity = dyRegisterService.findById(73);
            String user_cookie = dyUserEntity.getUserCookie();
            String simulationId = dyUserEntity.getSimulationID();


            //通过simulationid获取t_device中的数据
            DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
            String cookie = deviceEntity.getCookie();
            cookie += (";"+ user_cookie);
            deviceEntity.setCookie(cookie);

            //获取并构建url信息，包括host、msg、token
            UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);

            ThumbsUpMaker.thumbsUpMaker(aweme_id , deviceEntity, urlRequestEntity);

            try {
                Thread.sleep(1500);
                System.out.println(dyUserEntity.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        return "abc";
    }

    @RequestMapping("/follow")
    public String followMaker(){

        //这个是视频id，需要参数传入
        String user_id = "76612213462";

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(1);
        String mobile = dyUserEntity.getPhoneNum();
        String password = dyUserEntity.getPassword();
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(4);

        FollowMaker.FollowMaker(user_id, deviceEntity, urlRequestEntity);


        return null;
    }

    @RequestMapping("/modify")
    public String modificationMaker(){

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(2);
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();
        String uid = dyUserEntity.getUid();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity1 = urlRequestService.getUrlRequest(5);


        UrlRequestEntity urlRequestEntity2 = urlRequestService.getUrlRequest(8);

        ModifyInfoMaker.modifyInfoMaker(uid, deviceEntity, urlRequestEntity1, urlRequestEntity2);


        return null;
    }

    @RequestMapping("/support")
    public String supportAccountMaker(){

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(43);
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

//        //获取并构建url信息，包括host、msg、token
//        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(6);
//
//        ArrayList<String> awemeList =  SupportAccountMaker.getAwemeListMaker(deviceEntity, urlRequestEntity);
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        awemeList.add("6597344642847477000");
//        UrlRequestEntity urlRequestEntity1 = urlRequestService.getUrlRequest(7);
//
//        try {
//            for(int i = 0;i < awemeList.size();i++){
//                SupportAccountMaker.getVideoMaker(awemeList.get(i),deviceEntity, urlRequestEntity1);
//                Thread.sleep(5000);
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String a = "6597344642847477000";
        UrlRequestEntity urlRequestEntity1 = urlRequestService.getUrlRequest(7);

        try {
            for(int i = 0;i < 1000;i++){
                SupportAccountMaker.getVideoMaker(a,deviceEntity, urlRequestEntity1);
                Thread.sleep(800);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
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
