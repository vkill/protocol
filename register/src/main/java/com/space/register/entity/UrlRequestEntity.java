package com.space.register.entity;/**
 * @ Author     ：GXDTJJC
 * @ Date       ：Created in 14:26 2018/9/1/001
 * @ Description：从数据库获取与url相关内容的po
 * @ Modified By：
 * @Version: $version$
 */

import javax.persistence.*;

/**
 * @program: protool
 *
 * @description: 从数据库获取与url相关内容的po
 *
 * @author: Mr.gao
 *
 * @create: 2018-09-01 14:26
 **/
@Entity
@Table(name = "t_url_request")
public class UrlRequestEntity {

    @Id
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String host;

    private String message;

    @Column(columnDefinition = "text")
    private String header;

    @Column(columnDefinition = "text")
    private String body;

    public String getHost_address() {
        return host;
    }

    public void setHost_address(String host_address) {
        this.host = host_address;
    }

    public String getMessage_address() {
        return message;
    }

    public void setMessage_address(String message_address) {
        this.message = message_address;
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
