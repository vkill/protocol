package com.space.dyrev.systemprocess.diggandfollowprocess;

import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.request.applogmodule.service.impl.AppLogServiceImpl;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.request.operationmodule.service.impl.OperationServiceImpl;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                
 *        @Author: space
 *        @Date: 2018/10/28 14:56
 *        @Description: 
 **/
public class test {

    private static OperationService os = new OperationServiceImpl();

    private static AppLogService als = new AppLogServiceImpl();

    private static void testDigg () throws InterruptedException {
        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);
        DyUserEntity user = SaveAcc.getUser();

//        System.out.println(user);

        als.service2AppLog(okhttpClient, user.getDevice());

        Thread.sleep(1000);

        String digg = os.digg(okhttpClient, user, "6603651810504740109");

        

//        String follow = os.follow(okhttpClient, user, "101947485841");
//        System.out.println(follow);


    }

    public static void main(String[] args) {


        try {
            testDigg();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
