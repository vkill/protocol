package platform.thread;

import com.space.register.configurer.OrderThreadDatabaseImpl;
import com.space.register.configurer.RegisterThreadDatabaseImpl;
import com.space.register.controller.DeviceController;
import com.space.register.dao.DYUserRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.OrderEntity;
import jsonreader.tools.GzipGetteer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import params.*;
import platform.email.HostIPGetter;
import platform.threadManager.BussinessController;
import platform.tv.DeviceTvRegister;
import po.HostIPPo;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @program: protool
 * @description: 实现盈利业务的线程实现
 * @author: Mr.gao
 * @create: 2018-09-27 15:12
 **/
public class BussinessThread implements Runnable {

    public static int thread_num = 10;
    public static String successInfo ="success";
    public static String failureInfo = "failure";
    public ArrayList<OrderEntity> orderEntities;

    @Resource
    DYUserRepository dyUserRepository;

    OrderThreadDatabaseImpl orderThreadDatabase = new OrderThreadDatabaseImpl();
    public BussinessThread(ArrayList<OrderEntity> orderEntities){
        orderEntities = orderEntities;

    }

    @Override
    public void run() {
        do{
            HostIPPo hostIPPo = null;
            try {
                hostIPPo = BussinessController.hostIpQueneForBusiness.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            OkHttpClient okHttpClient;
            if(hostIPPo.port==0){
                okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                        .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
                        .build();
            }else{
                okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
                        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                        .connectTimeout(60,TimeUnit.SECONDS)//设置连接超时时间
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostIPPo.host, hostIPPo.port)))
                        .build();
            }
            DYUserEntity dyUserEntity ;
            OrderEntity orderEntity = BussinessController.orderEntities.get(0);
            //DeviceEntity deviceEntity = orderThreadDatabase.getDeviceByID(dyUserEntity.getId());



        }while (true);
    }

    public Boolean makeThumbUpAndFollow(OkHttpClient okHttpClient,DeviceEntity deviceEntity, DYUserEntity dyUserEntity, OrderEntity orderEntity) throws IOException {
        //这三个参数有用勿删
        int event_id;
        String session_id;
        long serverTime;
        //下面调用的appLog是加载某音app时候的appLog
        //随机生成的session_id
        session_id = AllAppLogConstruct.constructRandomSessionId();
        ArrayList<String> body_msg = AllAppLogConstruct.launchApp(dyUserEntity.getAppLog(), session_id, dyUserEntity.getEvent_id());
        //修改全部变量event_id
        event_id = Integer.valueOf(body_msg.get(0));
        //修改数据库中event_id的值
        dyUserEntity.setEvent_id(event_id);
        //修改全局变量serverTime
        serverTime = System.currentTimeMillis();
        String beforeThumb = AppLogMaker.app_log(okHttpClient,deviceEntity, dyUserEntity, body_msg.get(1));
        System.out.println(beforeThumb);
        if(orderEntity.getTypes().startsWith("dydz")){
            //digg就是封装后的点赞方法
            ArrayList<String> result = digg(okHttpClient, event_id, session_id, serverTime, dyUserEntity, deviceEntity, orderEntity);
            String status = result.get(0);
            event_id = Integer.valueOf(result.get(1));
            dyUserEntity.setEvent_id(event_id);
            if(status.equals(successInfo)){
                double nums = orderEntity.getThumbUpOrFollowNum();
                nums = nums - 1;
                orderEntity.setThumbUpOrFollowNum(nums);
                if(orderEntity.getThumbUpOrFollowNum()==0){
                    orderEntity.setStatus(0+"");
                    orderThreadDatabase.updataOrderInfo(orderEntity);
                    return true;
                }
            }else{
                result = digg(okHttpClient, event_id, session_id, serverTime, dyUserEntity, deviceEntity, orderEntity);
                status = result.get(0);
                event_id = Integer.valueOf(result.get(1));
                dyUserEntity.setEvent_id(event_id);
                if(status.equals(successInfo)){
                    //第二次跑如果成功
                    double nums = orderEntity.getThumbUpOrFollowNum();
                    nums = nums - 1;
                    orderEntity.setThumbUpOrFollowNum(nums);
                    if(orderEntity.getThumbUpOrFollowNum()==0){
                        orderEntity.setStatus(0+"");
                        orderThreadDatabase.updataOrderInfo(orderEntity);
                        return true;
                    }
                    return false;
                }else{
                    //第二次跑如果失败
                    return false;
                }
            }
        }else if(orderEntity.getTypes().startsWith("dygz")){

            //follow就是封装后的关注方法
            ArrayList<String> result = follow(okHttpClient, event_id, session_id, serverTime, dyUserEntity, deviceEntity, orderEntity);
            String status = result.get(0);
            event_id = Integer.valueOf(result.get(1));
            dyUserEntity.setEvent_id(event_id);

            if(status.equals(successInfo)){
                double nums = orderEntity.getThumbUpOrFollowNum();
                nums = nums - 1;
                orderEntity.setThumbUpOrFollowNum(nums);
                if(orderEntity.getThumbUpOrFollowNum()==0){
                    orderEntity.setStatus(0+"");
                    orderThreadDatabase.updataOrderInfo(orderEntity);
                    return true;
                }
            }else{

                ArrayList<String> result1 = follow(okHttpClient, event_id, session_id, serverTime, dyUserEntity, deviceEntity, orderEntity);
                String status1 = result1.get(0);
                event_id = Integer.valueOf(result1.get(1));
                dyUserEntity.setEvent_id(event_id);

                if(status1.equals(successInfo)){
                    //第二次跑如果成功
                    double nums = orderEntity.getThumbUpOrFollowNum();
                    nums = nums - 1;
                    orderEntity.setThumbUpOrFollowNum(nums);
                    if(orderEntity.getThumbUpOrFollowNum()==0){
                        orderEntity.setStatus(0+"");
                        orderThreadDatabase.updataOrderInfo(orderEntity);
                        return true;
                    }
                    boolean kao = false;
                    return kao;
                }else{
                    //第二次跑如果失败
                    return false;
                }

            }
        }
        return false;
    }

    public ArrayList<String> digg(OkHttpClient okHttpClient,  int event_id, String session_id, long serverTime, DYUserEntity dyUserEntity, DeviceEntity deviceEntity, OrderEntity orderEntity){
        //点赞操作  包括点赞和验证结果
        //这个aweme_id是视频id
        String aweme_id = orderEntity.getVideoID();
        long time = System.currentTimeMillis();
        ArrayList<String> result = ThumbsUpMaker.thumbsUpMaker(okHttpClient,aweme_id, deviceEntity, dyUserEntity);

        //下面几行用来判断是否点赞成功,status为success成功，为failure为失败
        String []line_list = result.get(0).split(", ");
        String status = "";
        for(int i = 0;i < line_list.length; i++){
            String []status_temp = line_list[i].split(":");
            if(status_temp[0].equals("\"is_digg\"")){
                char []array = status_temp[1].toCharArray();
                for(int j = 0;j < array.length; j++){
                    if (Character.isDigit(array[j])){
                        status += array[j];
                    }
                }
                if(status.equals("1")){
                    status = failureInfo;
                }else {
                    status = successInfo;
                }
                break;
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<String> digg_body_msg = AllAppLogConstruct.digg(dyUserEntity.getAppLog(), session_id, event_id, String.valueOf(serverTime), String.valueOf(time), dyUserEntity.getUid(), aweme_id, result.get(1));;

        event_id = Integer.valueOf(digg_body_msg.get(0));
//                String appLogResult = AppLogMaker.app_log(deviceEntity, dyUserEntity, digg_body_msg.get(1));
//                System.out.println(appLogResult);
        dyUserEntity.setEvent_id(event_id);
        //保存数据库，数据修改

        ArrayList<String> resultToReturn = new ArrayList<>();
        resultToReturn.add(status);
        resultToReturn.add(digg_body_msg.get(0));
        return resultToReturn;
    }

    public ArrayList<String> follow(OkHttpClient okHttpClient,  int event_id, String session_id, long serverTime, DYUserEntity dyUserEntity, DeviceEntity deviceEntity, OrderEntity orderEntity){
        //关注操作，包括关注和验证结果
        //需要关注的用户id
        String user_id = orderEntity.getVideoID();
        long time = System.currentTimeMillis();
        ArrayList<String> follow_result = FollowMaker.FollowMaker(okHttpClient,user_id, dyUserEntity, deviceEntity);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //这个方法用来获取该用户的一个视频id
        String result = SupportAccountMaker.getAwemeId(okHttpClient, deviceEntity, dyUserEntity, user_id);

        ArrayList<String> follow_body_msg = AllAppLogConstruct.follow(dyUserEntity.getAppLog(), session_id, event_id, String.valueOf(serverTime), String.valueOf(time), user_id, result, dyUserEntity.getUid(), follow_result.get(2));;

        event_id = Integer.valueOf(follow_body_msg.get(0));
//                String appLogResult = AppLogMaker.app_log(deviceEntity, dyUserEntity, follow_body_msg.get(1));
//                System.out.println(appLogResult);
        dyUserEntity.setEvent_id(event_id);
        //数据库存储操作


        //下面的代码来判断关注是否成功,status为success成功，为failure为失败
        String status = follow_result.get(1);

        ArrayList<String> resultToReturn = new ArrayList<>();
        resultToReturn.add(status);
        resultToReturn.add(follow_body_msg.get(0));
        return resultToReturn;
    }
}
