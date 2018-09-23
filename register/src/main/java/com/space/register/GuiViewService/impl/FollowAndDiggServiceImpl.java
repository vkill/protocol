package com.space.register.GuiViewService.impl;

import com.space.register.GuiViewService.FollowAndDiggService;
import com.space.register.configurer.Test;
import com.space.register.dao.DYUserRepository;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.UrlRequestService;
import httpmaker.ConstructRequest;
import jsonreader.tools.GzipGetteer;
import okhttp3.*;
import org.springframework.stereotype.Component;
import params.FollowMaker;
import params.ThumbsUpMaker;
import params.tools.ConstructRequestUrl;
import po.RequestTokenVo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 点赞实现的service层
 */
@Component
public class FollowAndDiggServiceImpl implements FollowAndDiggService {

    @Resource
    protected DYUserRepository DYUserRepository;
    @Resource
    protected DeviceRepository deviceRepository;
    @Resource
    protected UrlRequestRepository urlRequestRepository;
    private static FollowAndDiggServiceImpl fad;

    @PostConstruct
    public void init() {
        fad = this;
        fad.DYUserRepository = this.DYUserRepository;
        fad.deviceRepository = this.deviceRepository;
        fad.urlRequestRepository = this.urlRequestRepository;
    }


    @Override
    public void follow(String dyid, String userid, JTextArea textLog) {

//        textLog.append("-----关注id:" + userid + "----- 抖音号数据库id:"+ dyid + "-----\n");
//        String cookie = fad.deviceRepository.getDeviceMsgById(1).getCookie();
//        textLog.append(cookie + "\n");

        //这个是视频id，需要参数传入
//        String user_id = "104512020815";
        String user_id = "101947485841";
        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = fad.DYUserRepository.findById(Integer.parseInt(dyid));
        String mobile = dyUserEntity.getPhoneNum();
        String password = dyUserEntity.getPassword();
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = fad.deviceRepository.getDeviceMsgById(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";"+ user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = fad.urlRequestRepository.findUrlById(4);

//        FollowMaker.FollowMaker(user_id, deviceEntity, urlRequestEntity);

    }

    @Override
    public void digg(String dyid, String videoId, JTextArea log) {

        //这个是视频id，需要参数传入
        //String aweme_id = "6597344642847477000";
        //String aweme_id = "6602390827073277197";
        //String aweme_id = "6602797333635665156";
        //String aweme_id = "6599566234746883335";
        String aweme_id = "6602990623727291651";
        //String aweme_id = "6598666622192323844";

//        ArrayList<String> aaaa = new ArrayList<String>();
//        aaaa.add("6601079867616267528");
//        aaaa.add("6602390827073277197");
//        aaaa.add("6601800939487628558");
//        aaaa.add("6599566234746883335");
//        aaaa.add("6601500048515665166");

        //ArrayList<DYUserEntity> dyUserEntity_list = dyRegisterService.findAll();
        //for(int i = 0;i < dyUserEntity_list.size();i++) {
        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = fad.DYUserRepository.findById(Integer.parseInt(dyid));
        String user_cookie = dyUserEntity.getUserCookie();
        String simulationId = dyUserEntity.getSimulationID();


        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = fad.deviceRepository.getDeviceMsgById(Integer.parseInt(simulationId));
        String cookie = deviceEntity.getCookie();
        cookie += (";" + user_cookie);
        deviceEntity.setCookie(cookie);

        //获取并构建url信息，包括host、msg、token
        UrlRequestEntity urlRequestEntity = fad.urlRequestRepository.findUrlById(3);

        Map result = ThumbsUpMaker.thumbsUpMaker(aweme_id, deviceEntity, urlRequestEntity);

    }

    @Override
    public void modify(String dyid, String videoId, JTextArea log){};

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
