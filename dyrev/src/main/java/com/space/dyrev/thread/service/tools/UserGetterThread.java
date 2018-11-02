package com.space.dyrev.thread.service.tools;

import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.DyUserRepository;
import com.space.dyrev.thread.service.impl.UserThreadServiceImpl;
import com.space.dyrev.util.springutils.SpringUtil;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: hehedada
 * @description:
 * @author: Mr.gao
 * @create: 2018-10-21 20:58
 **/
public class UserGetterThread implements Runnable{

    public static int lessId = 0;
    public static int dyUserNum = 3000;
    public static int AlldyUserNum = 0;
    public static LinkedBlockingQueue linkedBlockingQueue;
    public static DyUserRepository dyUserRepository;

    public UserGetterThread(LinkedBlockingQueue linkedBlockingQueue){
        this.linkedBlockingQueue = linkedBlockingQueue;

    }

    @Override
    public void run() {
        int nowID = lessId;
        int refush =0;
        if(dyUserRepository == null){
            dyUserRepository = SpringUtil.getBean(DyUserRepository.class);
        }
        AlldyUserNum = dyUserRepository.getDyUserNum();
        while (true){
            refush++;
            if(linkedBlockingQueue.size()<dyUserNum){
                if(nowID>=AlldyUserNum){
                    nowID = lessId;
                }
                ArrayList<DyUserEntity> dyUserEntities =dyUserRepository.getUserByIdAndNum(nowID,dyUserNum);
                for(DyUserEntity dyUserEntity:dyUserEntities){
                    try {
                        linkedBlockingQueue.put(dyUserEntity);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                nowID+= dyUserEntities.size();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(refush>20){
                AlldyUserNum = dyUserRepository.getDyUserNum();
                refush =0;
            }
        }
    }


}
