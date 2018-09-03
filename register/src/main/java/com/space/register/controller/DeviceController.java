package com.space.register.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.space.register.dao.DeviceRepository;
import com.space.register.dao.UrlRequestRepository;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import com.space.register.entity.UrlRequestEntity;
import com.space.register.service.DYRegisterService;
import com.space.register.service.DeviceService;
import jsonreader.tools.GzipGetteer;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.email.EmailGetter;
import platform.main.TvRegisterMaker;
import po.PhonePo;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    @Resource
    DeviceRepository deviceRepository;

    @Resource
    UrlRequestRepository urlRequestRepository;

    @RequestMapping("/maker")
    public String deviceMain() {
//        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
//        DeviceEntity deviceEntity = tvRegisterMaker.registerUserToTv();
//        DeviceEntity save = deviceRepository.save(deviceEntity);
//        System.out.println(save.getCookie());
        TvRegisterMaker tvRegisterMaker = new TvRegisterMaker();
        OkHttpClient okHttpClient = tvRegisterMaker.okHttpClient;
        List<UrlRequestEntity> allUrl = urlRequestRepository.findAll();
        //为了测试而添加的读取方法
        List<DeviceEntity> allDevice = deviceRepository.findAll();
        DeviceEntity deviceEntity = allDevice.get(0);
        UrlRequestEntity urlRequestEntity= allUrl.get(0);
        UrlRequestEntity urlRequestEntity1 = allUrl.get(1);
        EmailGetter emailGetter = new EmailGetter();
        emailGetter.loginIT();
        PhonePo phonePo = emailGetter.getPhoneNumber();
        Request request = tvRegisterMaker.sendMessageForRegister(urlRequestEntity,deviceEntity,phonePo,"");
        Response response = null;
        String jsonString = null;
        JSONObject resultJson =null;
        System.out.println("输出请求信息");
        System.out.println(request.url());
        System.out.println(request.headers());
        System.out.println(request.body());
        System.out.println(request);
        try {
            response = okHttpClient.newCall(request).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
            System.out.println(jsonString);
            System.out.println(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送验证码失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送验证码失败");
        }
        String code = emailGetter.getIdentCode(phonePo.getP_ID());
        Request request1 = tvRegisterMaker.sendMessageForRegister(urlRequestEntity1,deviceEntity,phonePo,code);
        try {
            response = okHttpClient.newCall(request).execute();
            jsonString = GzipGetteer.uncompressToString(response.body().bytes());
            resultJson = new JSONObject(jsonString);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("注册用户结果获取失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册用户结果获取失败");
        }
        return "abc";
    }

    public boolean sendMessage(Request request, OkHttpClient okHttpClient){
        return false;
    }
}
