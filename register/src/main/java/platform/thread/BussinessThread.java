package platform.thread;

import com.space.register.configurer.OrderThreadDatabaseImpl;
import com.space.register.configurer.RegisterThreadDatabaseImpl;
import com.space.register.controller.DeviceController;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.OrderEntity;
import org.json.JSONException;
import platform.threadManager.BussinessController;
import po.HostIPPo;

import java.io.IOException;

/**
 * @program: protool
 * @description: 实现盈利业务的线程实现
 * @author: Mr.gao
 * @create: 2018-09-27 15:12
 **/
public class BussinessThread implements Runnable {

    public static int thread_num = 10;

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
            if("点赞".equals("点赞")){
                //点赞操作  包括点赞和2
                // .验证结果
            }else{
                //关注操作，包括关注和验证结果
            }

        }while (true);
    }

}
