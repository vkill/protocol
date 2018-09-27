package platform.thread;

import com.space.register.configurer.OrderThreadDatabaseImpl;
import com.space.register.configurer.RegisterThreadDatabaseImpl;
import com.space.register.controller.DeviceController;
import com.space.register.dao.DYUserRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.OrderEntity;
import org.json.JSONException;
import params.*;
import platform.threadManager.BussinessController;
import po.HostIPPo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: protool
 * @description: 实现盈利业务的线程实现
 * @author: Mr.gao
 * @create: 2018-09-27 15:12
 **/
public class BussinessThread implements Runnable {

    public static int thread_num = 10;

    @Resource
    DYUserRepository dyUserRepository;

    OrderThreadDatabaseImpl orderThreadDatabase = new OrderThreadDatabaseImpl();
    @Override
    public void run() {
        do{
            HostIPPo hostIPPo = null;
            if(BussinessController.hostIpQueneForBusiness.size()<= thread_num-1){
                DeviceController.getNeedIPFromWeb(BussinessController.hostIpQueneForBusiness);
            }
            try {
                hostIPPo = BussinessController.hostIpQueneForBusiness.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            DYUserEntity dyUserEntity = BussinessController.dyUserEntities.get(0);
            OrderEntity orderEntity = BussinessController.orderEntities.get(0);
            DeviceEntity deviceEntity = null;

            //这三个参数有用勿删
            int event_id = 0;
            String session_id = AllAppLogConstruct.constructRandomSessionId();
            long serverTime = 0;

            //下面调用的appLog是加载某音app时候的appLog

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
            String launchResult = AppLogMaker.app_log(deviceEntity, dyUserEntity, body_msg.get(1));
            System.out.println(launchResult);

            if("点赞".equals("点赞")){
                //点赞操作  包括点赞和验证结果

                //这个aweme_id是视频id
                String aweme_id = "";
                long time = System.currentTimeMillis();
                ArrayList<String> result = ThumbsUpMaker.thumbsUpMaker(aweme_id, deviceEntity, dyUserEntity);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<String> digg_body_msg = AllAppLogConstruct.digg(dyUserEntity.getAppLog(), session_id, event_id, String.valueOf(serverTime), String.valueOf(time), dyUserEntity.getUid(), aweme_id, result.get(1));;

                event_id = Integer.valueOf(body_msg.get(0));
                String appLogResult = AppLogMaker.app_log(deviceEntity, dyUserEntity, digg_body_msg.get(1));
                System.out.println(appLogResult);
                dyUserEntity.setEvent_id(event_id);
                //保存数据库，数据修改
                dyUserRepository.save(dyUserEntity);

            }else{
                //关注操作，包括关注和验证结果

                //需要关注的用户id
                String user_id = "";
                long time = System.currentTimeMillis();
                ArrayList<String> follow_result = FollowMaker.FollowMaker(user_id, dyUserEntity, deviceEntity);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //这个方法用来获取该用户的一个视频id
                String result = SupportAccountMaker.getAwemeId(deviceEntity, dyUserEntity, user_id);

                ArrayList<String> follow_body_msg = AllAppLogConstruct.follow(dyUserEntity.getAppLog(), session_id, event_id, String.valueOf(serverTime), String.valueOf(time), user_id, result, dyUserEntity.getUid(), follow_result.get(2));;

                event_id = Integer.valueOf(body_msg.get(0));
                String appLogResult = AppLogMaker.app_log(deviceEntity, dyUserEntity, follow_body_msg.get(1));
                System.out.println(appLogResult);
                dyUserEntity.setEvent_id(event_id);
                //数据库存储操作
                dyUserRepository.save(dyUserEntity);
            }

        }while (true);
    }

}
