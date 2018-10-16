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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: protool
 * @description: 实现盈利业务的线程实现
 * @author: Mr.gao
 * @create: 2018-09-27 15:12
 **/
public class BussinessThread implements Runnable {

    public static int thread_num = 2;
    public static String successInfo ="success";
    public static String failureInfo = "failure";
    public static int oneIpThumbUp = 30;

    public ArrayList<OrderEntity> orderEntities;
    public ArrayList<DYUserEntity> dyUserEntities;

    OrderThreadDatabaseImpl orderThreadDatabase = new OrderThreadDatabaseImpl();
    public BussinessThread(ArrayList<OrderEntity> orderEntitiy,ArrayList<DYUserEntity> dyUserEntities1){
        orderEntities = new ArrayList<>(orderEntitiy);
        dyUserEntities = new ArrayList<>(dyUserEntities1);
    }

    @Override
    public void run() {
        OkHttpClient okHttpClient = null;
        DYUserEntity dyUserEntity ;
        DeviceEntity deviceEntity;
        OrderEntity orderEntity;
        int ipInfo = 0;
        int userNum = 0;
        int orderEntitySize = orderEntities.size();
        do{
            if(userNum>=BussinessController.dyUserEntities.size()){
                System.out.println("用户使用完毕");
                for(OrderEntity orderEntity1:orderEntities){
                    orderEntity1.setStatus("-1");
                    orderThreadDatabase.updataOrderInfo(orderEntity1);
                }
                for(DYUserEntity dyUserEntity1:dyUserEntities){
                    orderThreadDatabase.saveDyUser(dyUserEntity1);
                }
                break;
            }else if(orderEntities.size()==0){
                System.out.println("订单全部完成");
                for(DYUserEntity dyUserEntity1:dyUserEntities){
                    orderThreadDatabase.saveDyUser(dyUserEntity1);
                }
                break;
            }
            dyUserEntity =dyUserEntities.get(userNum);
            deviceEntity = orderThreadDatabase.getDeviceByID(Integer.parseInt(dyUserEntity.getSimulationID()));
            if(ipInfo%oneIpThumbUp==0){
                okHttpClient = changeOkHttpHost();
            }

            for(int i =0;i<orderEntitySize;i++){
                orderEntity = orderEntities.get(i);
                try {
                    if(makeThumbUpAndFollow(okHttpClient,deviceEntity,dyUserEntity,orderEntity)){
                        System.out.println("订单点赞完成");
                        orderEntities.remove(i);
                        i--;
                        orderEntitySize = orderEntities.size();
                    }
                    ipInfo++;
                    if(ipInfo%oneIpThumbUp==0){
                        okHttpClient = changeOkHttpHost();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(i<=0){
                        userNum--;
                        ipInfo=0;
                        break;

                    }
                    else{
                        okHttpClient =changeOkHttpHost();
                        i--;
                        ipInfo=0;
                        continue;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(i<=0){
                        userNum--;
                        ipInfo=0;
                        break;

                    }
                    else{
                        okHttpClient =changeOkHttpHost();
                        i--;
                        ipInfo=0;
                        continue;
                    }
                }
            }
            orderThreadDatabase.saveDyUser(dyUserEntity);
            userNum++;




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
                int nums = orderEntity.getThumbUpOrFollowNum();
                nums = nums - 1;
                orderEntity.setThumbUpOrFollowNum(nums);
                dyUserEntity.setUsed_turn(dyUserEntity.getUsed_turn()+1);
                if(orderEntity.getThumbUpOrFollowNum()==0){
                    return finallyOrderAction(dyUserEntity,orderEntity);
                }
            }else{
                result = digg(okHttpClient, event_id, session_id, serverTime, dyUserEntity, deviceEntity, orderEntity);
                status = result.get(0);
                event_id = Integer.valueOf(result.get(1));
                dyUserEntity.setEvent_id(event_id);
                if(status.equals(successInfo)){
                    //第二次跑如果成功
                    int nums = orderEntity.getThumbUpOrFollowNum();
                    nums = nums - 1;
                    orderEntity.setThumbUpOrFollowNum(nums);
                    //dyUserEntity.setUsed_turn(dyUserEntity.getUsed_turn()+1);
                    if(orderEntity.getThumbUpOrFollowNum()==0){
                        return finallyOrderAction(dyUserEntity,orderEntity);
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
                int nums = orderEntity.getThumbUpOrFollowNum();
                nums = nums - 1;
                orderEntity.setThumbUpOrFollowNum(nums);

                dyUserEntity.setUsed_turn(dyUserEntity.getUsed_turn()+1);
                if(orderEntity.getThumbUpOrFollowNum()==0){
                    return finallyOrderAction(dyUserEntity,orderEntity);
                }
            }else{

                ArrayList<String> result1 = follow(okHttpClient, event_id, session_id, serverTime, dyUserEntity, deviceEntity, orderEntity);
                String status1 = result1.get(0);
                event_id = Integer.valueOf(result1.get(1));
                dyUserEntity.setEvent_id(event_id);

                if(status1.equals(successInfo)){
                    //第二次跑如果成功
                    int nums = orderEntity.getThumbUpOrFollowNum();
                    nums = nums - 1;
                    orderEntity.setThumbUpOrFollowNum(nums);

                    if(orderEntity.getThumbUpOrFollowNum()==0){
                        return finallyOrderAction(dyUserEntity,orderEntity);
                    }
                    boolean kao = false;
                    return kao;
                }else{
                    //第二次跑如果失败
                    return false;
                }

            }
        }
        //第二次跑如果成功
        int nums = orderEntity.getThumbUpOrFollowNum();
        nums = nums - 1;
        orderEntity.setThumbUpOrFollowNum(nums);
        if(orderEntity.getThumbUpOrFollowNum()==0){
            return finallyOrderAction(dyUserEntity,orderEntity);
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

    public static void getNeedIPFromWeb(LinkedBlockingQueue<HostIPPo> hostIpQuene){
        ArrayList<HostIPPo> hostIPPos = HostIPGetter.getIpByXdali(thread_num);
        if(hostIpQuene.size()>thread_num*2-1){
            return;
        }
        if(hostIPPos == null){
            System.out.println("IP用完了，结束线程");
            return;
        }
        for(HostIPPo hostIPPo:hostIPPos){
            try {
                hostIpQuene.put(hostIPPo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(hostIpQuene.size()<RegisterThread.thread_num*2-1){
            getNeedIPFromWeb(hostIpQuene);
        }
    }

    //修改okhttp代理的方法
    public static OkHttpClient changeOkHttpHost(){
        HostIPPo hostIPPo;
        try {
            hostIPPo = BussinessController.hostIpQueneForBusiness.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return changeOkHttpHost();
        }
        if(BussinessController.hostIpQueneForBusiness.size()==thread_num-1){
            synchronized (BussinessController.hostIpQueneForBusiness){
                if(BussinessController.hostIpQueneForBusiness.size()<=thread_num-1){
                    getNeedIPFromWeb(BussinessController.hostIpQueneForBusiness);
                }
            }

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
        return new OkHttpClient();
    }

    public boolean finallyOrderAction(DYUserEntity dyUserEntity,OrderEntity orderEntity){
        orderEntity.setStatus(0+"");
        orderEntity.setLangestDYId(dyUserEntity.getId());
        orderThreadDatabase.updataOrderInfo(orderEntity);
        return true;
    }
}
