package platform.threadManager;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.OrderEntity;
import platform.email.HostIPGetter;
import platform.thread.RegisterThread;
import po.HostIPPo;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: protool
 * @description: 控制点赞线程组的实现类
 * @author: Mr.gao
 * @create: 2018-09-27 17:28
 **/
public class BussinessController {

    public static LinkedBlockingQueue<HostIPPo> hostIpQueneForBusiness = new LinkedBlockingQueue<HostIPPo>();

    public static Vector<DYUserEntity> dyUserEntities = new Vector<>();

    public static Vector<OrderEntity> orderEntities = new Vector<OrderEntity>();

    public void doBusinessWork(){

    }

}
