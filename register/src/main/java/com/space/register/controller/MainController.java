package com.space.register.controller;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;
import com.space.register.service.UrlRequestService;
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

    private static int id = 333;

    /**
     * 点赞模块
     * @return
     */
    @RequestMapping("/thumbsUp")
    public Map thumbsUpMaker() {

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
           DYUserEntity dyUserEntity = dyRegisterService.findById(id);
           String user_cookie = dyUserEntity.getUserCookie();
           String simulationId = dyUserEntity.getSimulationID();


           //通过simulationid获取t_device中的数据
           DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));
           String cookie = deviceEntity.getCookie();
           cookie += (";" + user_cookie);
           deviceEntity.setCookie(cookie);

           //获取并构建url信息，包括host、msg、token
           UrlRequestEntity urlRequestEntity = urlRequestService.getUrlRequest(3);

           Map result = ThumbsUpMaker.thumbsUpMaker(aweme_id, deviceEntity, urlRequestEntity);


//           try {
//               for(int i = 0;i < aaaa.size();i++){
//                   Map result = ThumbsUpMaker.thumbsUpMaker(aaaa.get(i), deviceEntity, urlRequestEntity);
//                   Thread.sleep(500);
//               }
//               Thread.sleep(5000);
//               System.out.println(dyUserEntity.getId());
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }



        return result;
    }

    @RequestMapping("/follow")
    public String followMaker(){

        //这个是视频id，需要参数传入
//        String user_id = "104512020815";
        String user_id = "104578018304";
        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));

        FollowMaker.FollowMaker(user_id, dyUserEntity, deviceEntity);


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
        UrlRequestEntity urlRequestEntity1 = urlRequestService.getUrlRequest(5);


        UrlRequestEntity urlRequestEntity2 = urlRequestService.getUrlRequest(8);

        ModifyInfoMaker.modifyInfoMaker(uid, deviceEntity, urlRequestEntity1, urlRequestEntity2);


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

        ArrayList<String> awemeList =  SupportAccountMaker.getAwemeListMaker(deviceEntity, urlRequestEntity);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        awemeList.add("6602390827073277197");
        UrlRequestEntity urlRequestEntity1 = urlRequestService.getUrlRequest(7);

        try {
            for(int i = 0;i < awemeList.size();i++){
                SupportAccountMaker.getVideoMaker(awemeList.get(i),deviceEntity, urlRequestEntity1);
                Thread.sleep(2000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/appLog")
    public String mainController() throws InterruptedException {

        //通过id获取t_dy_user中的数据
        DYUserEntity dyUserEntity = dyRegisterService.findById(id);
        String simulationId = dyUserEntity.getSimulationID();

        //通过simulationid获取t_device中的数据
        DeviceEntity deviceEntity = deviceService.getDeviceMsg(Integer.parseInt(simulationId));

        AppLogMaker.app_log(deviceEntity, dyUserEntity, String.valueOf(System.currentTimeMillis() - 180000));
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
