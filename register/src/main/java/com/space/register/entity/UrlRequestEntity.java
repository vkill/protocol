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

    private String host;

    private String message;

    @Column(columnDefinition = "text")
    private String header;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "UrlRequestEntity{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", message='" + message + '\'' +
                ", header='" + header + '\'' +
                '}';
    }
}
