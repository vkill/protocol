package com.space.dyrev.systemprocess.diggandfollowprocess.service.impl;

import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.dao.DyUserRepository;
import com.space.dyrev.request.applogmodule.service.AppLogService;
import com.space.dyrev.systemprocess.diggandfollowprocess.service.OperationService;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
 *        @Date: 2018/10/29 21:00
 *        @Description: 
 **/
@Service("digg")
public class OperationServiceImpl implements OperationService {

    @Resource
    com.space.dyrev.request.operationmodule.service.OperationService operationService;

    @Resource
    DyUserRepository dyUserRepository;

    @Resource
    AppLogService appLogService;


    @Override
    public void digg(OkHttpClient okHttpClient, Integer id, String awemeId) {
        DyUserEntity dyUserEntity = dyUserRepository.findById(id).get();
        appLogService.service2AppLog(okHttpClient, dyUserEntity.getDevice());
        operationService.digg(okHttpClient, dyUserEntity, awemeId);
    }
}
