package params.tools;

/**
 * @program: protocol
 * @description: 密钥管理类，目前使用bug的参数方式
 * @author: gaoxiang
 * @create: 2018-08-26 15:41
 **/
public class KeyControler {

    private static String as = "a1iosdfgh";
    private static String cp = "androide1";
    private static String mas = null;

    public static String getKeyForUse(){
        StringBuilder result = new StringBuilder();
        result.append("&as="+as);
        result.append("&cp="+cp);
        return  result.toString();
    }
}
