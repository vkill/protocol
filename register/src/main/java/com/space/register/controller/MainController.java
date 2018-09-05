package com.space.register.controller;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;
import com.space.register.service.UrlRequestService;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import params.FollowMaker;
import params.GetAwemeListMaker;
import params.ModifyInfoMaker;
import params.ThumbsUpMaker;
import params.tools.ConstructRequestUrl;
import po.RequestTokenVo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

        ArrayList<DYUserEntity> dyUserEntity_list = dyRegisterService.findAll();
        for(int i = 0;i < dyUserEntity_list.size(); i++){

            //通过id获取t_dy_user中的数据
            DYUserEntity dyUserEntity = dyUserEntity_list.get(i);
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
            UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);

            ThumbsUpMaker.thumbsUpMaker(aweme_id , deviceEntity, urlRequestEntity);

            try {
                Thread.sleep(1500);
                System.out.println(dyUserEntity.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        DYUserEntity dyUserEntity = dyRegisterService.findById(43);
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();
        String uid = dyUserEntity.getUid();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(5);

        ModifyInfoMaker.modifyInfoMaker(uid, deviceEntity, urlRequestEntity);


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

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(6);

        ArrayList<String> awemeList =  GetAwemeListMaker.getAwemeListMaker(deviceEntity, urlRequestEntity);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(awemeList);
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
