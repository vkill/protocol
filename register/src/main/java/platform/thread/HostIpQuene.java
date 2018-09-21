package platform.thread;

import po.HostIPPo;

import javax.management.Query;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: protool
 * @description: 存储host的队列实现,也是线程的实现类
 * @author: Mr.gao
 * @create: 2018-09-21 17:27
 **/
public class HostIpQuene {
    public static int thread_num = 5;
    public static LinkedBlockingQueue<HostIPPo> hostIpQuene = new LinkedBlockingQueue<HostIPPo>();


}
