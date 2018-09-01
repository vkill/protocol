package params;

import java.util.Iterator;
import java.util.Map;

/**
 * @program: register
 * @description: 用于生成request请求
 * @author: Mr.Jia
 * @create: 2018-09-01 13:54
 **/
public class ConstructRequestUrl {

    /**
     * 通过输入参数：host，requestMsg，requestToken构造用于请求的url
     * @param host
     * @param requestMsg
     * @param requestToken
     * @return
     */
    public String constructUrl(String host, String requestMsg, Map<String,Object> requestToken){

        String url = "";
        url += host;
        url += requestMsg;
        url += MapToString(requestToken);

        return url;
    }


    public static String MapToString(Map map){
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "=" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "&" : "");
        }
        return sb.toString();
    }
}
