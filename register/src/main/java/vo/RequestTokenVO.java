package vo;


import java.util.Map;

/**
 * @program: register
 * @description: request请求信息的VO类
 * @author: Mr.Jia
 * @create: 2018-09-01 11:10
 **/

public class RequestTokenVO {
    private String Url;
    private Map<String, String> header;
    private Map<String, String> body;
    private Map<String, String> cookie;
    public RequestTokenVO(){

    }

    public RequestTokenVO(String url, Map<String, String> header, Map<String, String> body){
        this.Url = url;
        this.header = header;
        this.body = body;
    }
    //setter&getter

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    //toString
    @Override
    public String toString() {
        return "RequestTokenVO{" +
                "Url='" + Url + '\'' +
                ", header=" + header +
                ", body=" + body +
                '}';
    }

//    public String changeToRequestToken(String name ){
//
//        RequestTokenVO token = new RequestTokenVO(null,null,null);
//        String requestToken = "";
//        String url = token.getUrl();
//        Map <String, String> header = token.getHeader();
//        Map <String, String> body = token.getBody();
//
//
//        return requestToken;
//    }

}
