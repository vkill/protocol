package platform.main;

import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import enums.paramtable.DirTable;
import enums.paramtable.urltools.URLmakeTools;
import httpmaker.ConstructRequest;
import okhttp3.Request;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy;
import params.ParamCreater;
import params.tools.KeyControler;
import po.RequestTokenVo;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: protool
 * @description: 用户权限获取类
 * @author: Mr.gao
 * @create: 2018-09-10 19:18
 **/
public class UserPowerGetter {

    public static Request registerFriends(DeviceEntity deviceEntity,DYUserEntity dyUserEntity){
        //获取关注权限的url请求
        RequestTokenVo requestTokenVo;
        requestTokenVo =getFriendSTokenVo(deviceEntity,dyUserEntity);

        return ConstructRequest.constructPost(requestTokenVo);
    }

    private static RequestTokenVo getFriendSTokenVo(DeviceEntity deviceEntity, DYUserEntity dyUserEntity){
        RequestTokenVo requestTokenVo;
        //发送真是设备验证http请求
        Map realInfoMap = URLmakeTools.url_split(DirTable.friend_Register);
        Map<String,String> infoChange = new HashMap<String,String>();
        infoChange.put("device_type",deviceEntity.getDevice_type());
        infoChange.put("iid",deviceEntity.getIid());
        infoChange.put("uuid",deviceEntity.getUuid());
        infoChange.put("openudid",deviceEntity.getOpenudid());
        infoChange.put("device_id",deviceEntity.getDeviceId());
        infoChange.put("device_brand",deviceEntity.getDevice_brand());
        String sysTimes = ParamCreater.get_Rticket();
        infoChange.put("_rticket",sysTimes);
        infoChange.put("ts",ParamCreater.get_Ts(sysTimes));
        String realInfoUrl = DirTable.friend_Register_Hoster+UrlBodyCreaterTool.getBodyByMapAndMap(realInfoMap,infoChange)+ KeyControler.getKeyForUse();
        requestTokenVo = new RequestTokenVo();
        //生成请求头信息
        Map<String,String> realDeviceHeader = new HashMap<String,String>();

        realDeviceHeader.put("Accept-Encoding","gzip");
        realDeviceHeader.put("Cache-Control","max-stale=0");
        realDeviceHeader.put("Content-Type","application/x-www-form-urlencoded");
        realDeviceHeader.put("Host","api.amemv.com");
        realDeviceHeader.put("Connection","Keep-Alive");
        realDeviceHeader.put("Cookie",dyUserEntity.getUserCookie());
        realDeviceHeader.put("User-Agent","okhttp/3.8.1");

        //生成body信息
        Map<String,String> realDeviceBody = new HashMap<String,String>();
        realDeviceBody.put("phone_number","+"+dyUserEntity.getPhoneArea()+dyUserEntity.getPhoneNum());
        realDeviceBody.put("device_type",deviceEntity.getDevice_type());
        realDeviceBody.put("iid",deviceEntity.getIid());
        realDeviceBody.put("uuid",deviceEntity.getUuid());
        realDeviceBody.put("openudid",deviceEntity.getOpenudid());
        realDeviceBody.put("device_id",deviceEntity.getDeviceId());
        realDeviceBody.put("device_brand",deviceEntity.getDevice_brand());
        realDeviceBody.put("_rticket", ParamCreater.get_Rticket());
        Map<String,String> bodyMap = URLmakeTools.url_split(DirTable.friend_Register_Body);
        realDeviceBody = URLmakeTools.url_split(UrlBodyCreaterTool.getBodyByMapAndMap(bodyMap,realDeviceBody));
        requestTokenVo.setUrl(realInfoUrl);
        requestTokenVo.setHeader(realDeviceHeader);
        requestTokenVo.setBody(realDeviceBody);
        return requestTokenVo;
    }

    public static Request abTestParams(DeviceEntity deviceEntity,DYUserEntity dyUserEntity){
        RequestTokenVo requestTokenVo;
        //发送真是设备验证http请求
        Map realInfoMap = URLmakeTools.url_split(DirTable.abtest_Param);
        Map<String,String> infoChange = new HashMap<String,String>();
        infoChange.put("device_type",deviceEntity.getDevice_type());
        infoChange.put("iid",deviceEntity.getIid());
        infoChange.put("device_id",deviceEntity.getDeviceId());
        infoChange.put("uuid",deviceEntity.getUuid());
        infoChange.put("openudid",deviceEntity.getOpenudid());
        infoChange.put("device_brand",deviceEntity.getDevice_brand());
        String sysTimes = ParamCreater.get_Rticket();
        infoChange.put("_rticket",sysTimes);
        infoChange.put("ts",ParamCreater.get_Ts(sysTimes));
        String realInfoUrl = DirTable.abtest_Param_Hoster+UrlBodyCreaterTool.getBodyByMapAndMap(realInfoMap,infoChange)+ KeyControler.getKeyForUse();
        requestTokenVo = new RequestTokenVo();
        //生成请求头信息
        Map<String,String> realDeviceHeader = new HashMap<String,String>();

        realDeviceHeader.put("Accept-Encoding","gzip");
        realDeviceHeader.put("Cache-Control","max-stale=0");
        realDeviceHeader.put("Content-Type","application/x-www-form-urlencoded");
        realDeviceHeader.put("Host","api.amemv.com");
        realDeviceHeader.put("Connection","Keep-Alive");
        realDeviceHeader.put("Cookie",dyUserEntity.getUserCookie());
        realDeviceHeader.put("User-Agent","okhttp/3.8.1");

        requestTokenVo.setUrl(realInfoUrl);
        requestTokenVo.setHeader(realDeviceHeader);
        return ConstructRequest.constructGet(requestTokenVo);

    }

    public static Request messageAccount(DeviceEntity deviceEntity,DYUserEntity dyUserEntity){
        RequestTokenVo requestTokenVo;
        //发送真是设备验证http请求
        Map realInfoMap = URLmakeTools.url_split(DirTable.message_Account);
        Map<String,String> infoChange = new HashMap<String,String>();
        infoChange.put("device_type",deviceEntity.getDevice_type());
        infoChange.put("iid",deviceEntity.getIid());
        infoChange.put("device_id",deviceEntity.getDeviceId());
        infoChange.put("uuid",deviceEntity.getUuid());
        infoChange.put("openudid",deviceEntity.getOpenudid());
        infoChange.put("device_brand",deviceEntity.getDevice_brand());
        String sysTimes = ParamCreater.get_Rticket();
        infoChange.put("_rticket",sysTimes);
        infoChange.put("ts",ParamCreater.get_Ts(sysTimes));
        String realInfoUrl = DirTable.abtest_Param_Hoster+UrlBodyCreaterTool.getBodyByMapAndMap(realInfoMap,infoChange)+ KeyControler.getKeyForUse();
        requestTokenVo = new RequestTokenVo();
        //生成请求头信息
        Map<String,String> realDeviceHeader = new HashMap<String,String>();

        realDeviceHeader.put("Accept-Encoding","gzip");
        realDeviceHeader.put("Cache-Control","max-stale=0");
        realDeviceHeader.put("Content-Type","application/x-www-form-urlencoded");
        realDeviceHeader.put("Host","api.amemv.com");
        realDeviceHeader.put("Connection","Keep-Alive");
        realDeviceHeader.put("Cookie",dyUserEntity.getUserCookie());
        realDeviceHeader.put("User-Agent","okhttp/3.8.1");

        requestTokenVo.setUrl(realInfoUrl);
        requestTokenVo.setHeader(realDeviceHeader);
        return ConstructRequest.constructGet(requestTokenVo);

    }
    // 思考到更好的替代方案
//    public static Request checkInGetAccount(DeviceEntity deviceEntity,DYUserEntity dyUserEntity){
//        //实现参数的构造就行
//
//        return null;
//    }
}
