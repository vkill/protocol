package po;

/**
 * @program: protool
 *
 * @description: 从数据库获取与url相关内容的po
 *
 * @author: Mr.gao
 *
 * @create: 2018-09-01 14:26
 **/
public class UrlRequestPo {

    private String host_address;
    private String message_address;
    private String header;
    private String body;

    public String getHost_address() {
        return host_address;
    }

    public void setHost_address(String host_address) {
        this.host_address = host_address;
    }

    public String getMessage_address() {
        return message_address;
    }

    public void setMessage_address(String message_address) {
        this.message_address = message_address;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
