package platform.tv;

import enums.BaseAppInfo;
import enums.ParamsInfoList;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import params.tools.KeyControler;
import params.ParamCreater;
import enums.paramtable.DirTable;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @program: protocol
 * @description: 抖音协议注册类
 * @author: gaoxiang
 * @create: 2018-08-26 10:45
 **/
public class LoginTV {

    //public SimulationInfoGetter simulationInfoGetter = null;
    public ParamCreater paramCreater = null;
    //测试参数


    public LoginTV(){
        //simulationInfoGetter = new SimulationInfoGetter();
        paramCreater = new ParamCreater();
    }

    /**
     *
     * @param beginURL 开头网址
     * @return 加入基本app的网址
     */
    public String addBaseAppInfo(String beginURL){
        StringBuilder buffer = new StringBuilder();
        buffer.append(beginURL);
        for(BaseAppInfo baseAppInfo: BaseAppInfo.values()){
            if(baseAppInfo.getVaule().equals("version_name")){
                buffer.append(baseAppInfo.getVaule()+"="+ DirTable.base_APPInfo_Table.get(baseAppInfo));
            }
            else if(DirTable.base_APPInfo_Table.get(baseAppInfo).isEmpty()|DirTable.base_APPInfo_Table.get(baseAppInfo).equals(null)){
                buffer.append("&"+baseAppInfo.getVaule()+"=" );
            }else{
                buffer.append("&"+baseAppInfo.getVaule()+"="+DirTable.base_APPInfo_Table.get(baseAppInfo));
            }
        }
        return buffer.toString();
    }
/**
    public String addSimulationInfo(String baseInfoURL,SimulationPo simulationPo){
        StringBuilder buffer = new StringBuilder();
        buffer.append(baseInfoURL);

        for(BaseSimulationInfo baseSimulationInfo: BaseSimulationInfo.values()){

            buffer.append("&"+baseSimulationInfo.getVaule()+"="+simulationPo.getByName(baseSimulationInfo.getVaule()));

        }
        return buffer.toString();
    }
**/
    public String addParamsCreatBySelf(String simulationURL,String aremPhone,String phoneNum){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(simulationURL);
        String numOne = "null";
        String numTwo = "null";
        int i =0;
        for(ParamsInfoList paramsInfoList:ParamsInfoList.values()){
            stringBuilder.append("&"+paramsInfoList.getVaule()+"="+paramCreater.paramsControler(numOne,numTwo));
            if(i ==0){
                numOne = paramCreater.paramsControler(numOne,numTwo);
            }else if(i==1){
                numOne = aremPhone;
                numTwo = phoneNum;
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public String addKeyUrl(String selfParamsURL){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(selfParamsURL);
        stringBuilder.append(KeyControler.getKeyForUse());
        return  stringBuilder.toString();
    }

    public static void main(String[] args){
        String phone = "18805156570";
        String TopURL = "https://i.snssdk.com/passport/mobile/send_code/?";
        LoginTV loginTV = new LoginTV();
        //System.out.println(loginTV.paramCreater.get_Mobile("86",phone));

        String string = loginTV.addBaseAppInfo(TopURL);
        //string = loginTV.addSimulationInfo(string,loginTV.simulationInfoGetter.getAllSimulation().get(0));
        string = loginTV.addParamsCreatBySelf(string,"86",phone);
        string =loginTV.addKeyUrl(string);
        System.out.println(string);
        System.out.println("");
        /**
        try {
            loginTV.buildHttpGet(string);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        **/
    }
    public   HttpGet buildHttpGet(String url)
            throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);

            //builder.setParameter(key, para.get(key));
        HttpGet request = new HttpGet(builder.build());
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(6000)
                .setConnectTimeout(6000)
                .setConnectionRequestTimeout(6000).build();
        request.setConfig(requestConfig);
        System.out.println(request.getURI().toString());
        System.out.println("");
        CloseableHttpClient httpCilent2 = HttpClients.createDefault();
        try {
            HttpResponse httpResponse = httpCilent2.execute(request);
            String srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
            System.out.println(srtResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return request;
    }
}
