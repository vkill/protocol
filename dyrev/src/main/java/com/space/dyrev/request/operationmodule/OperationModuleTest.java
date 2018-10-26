package com.space.dyrev.request.operationmodule;

import com.alibaba.fastjson.JSONObject;
import com.space.dyrev.commonentity.DeviceEntity;
import com.space.dyrev.commonentity.DyUserEntity;
import com.space.dyrev.commonentity.PhoneEntity;
import com.space.dyrev.dao.SaveAcc;
import com.space.dyrev.enumeration.OkhttpType;
import com.space.dyrev.enumeration.PhoneArea;
import com.space.dyrev.request.operationmodule.service.OperationService;
import com.space.dyrev.request.operationmodule.service.impl.OperationServiceImpl;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;

import static com.space.dyrev.dao.SaveAcc.getUser;

/**
 * @program: dyrev
 * @description: 操作类的测试
 * @author: Mr.Jia
 * @create: 2018-10-26 22:40
 **/
public class OperationModuleTest {

    private static OperationService os = new OperationServiceImpl();

    public static void modify(OkHttpClient okHttpClient, DyUserEntity dyUserEntity){
        os.modify(okHttpClient, dyUserEntity);
    }

    public static void main(String[] args) {

        OkHttpClient okhttpClient = OkHttpTool.getOkhttpClient(OkhttpType.PROXY);

        DeviceEntity deviceEntity = SaveAcc.getDevice();

        deviceEntity.setDeviceBrand("Meizu");
        deviceEntity.setDeviceId("41336725255");
        deviceEntity.setDeviceType("MX5");
        deviceEntity.setOpenudid("ef8ad7929c2e0994");
        deviceEntity.setUuid("867246022383583");
        deviceEntity.setInstallId("47832967227");

        DyUserEntity dyUserEntity = new DyUserEntity();
        dyUserEntity.setDevice(deviceEntity);
        dyUserEntity.setAccount("102365947722");
        dyUserEntity.setxTtToken("");
        dyUserEntity.setxTtTokenSign("");

        JSONObject a = new JSONObject();
        a.put("odin_tt","93ab97ee715dc15870e3286ccca4403e331a3efa40838012fc5d9c8865fb732fddbd299351f07cdb410ceb3c464e6a13");
        a.put("sid_guard","f270feac5795308da14d69f34c4c7901%7C1540563229%7C5184000%7CTue%2C+25-Dec-2018+14%3A13%3A49+GMT");
        a.put("uid_tt","ecae1459e7369df49fd856983e35f37d");
        a.put("sid_tt","f270feac5795308da14d69f34c4c7901");
        a.put("sessionid","f270feac5795308da14d69f34c4c7901");

        String aaa = a.toJSONString();
        System.out.println(aaa);
        dyUserEntity.setUserCookies(aaa);
        modify(okhttpClient, dyUserEntity);
    }
}
