package po;


import java.util.Map;

public class RequestTokenVO {
    private String Url;
    private Map<String, String> header;
    private Map<String, String> body;


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

    //toString
    @Override
    public String toString() {
        return "RequestTokenVO{" +
                "Url='" + Url + '\'' +
                ", header=" + header +
                ", body=" + body +
                '}';
    }

//    public RequestTokenVO(String url, Map<String, String> header, Map<String, String> body){
//        this.Url = url;
//        this.header = header;
//        this.body = body;
//    }

    public RequestTokenVO changeToRequestToken(String name ){

        RequestTokenVO token =new RequestTokenVO();

        return token;
    }

}
