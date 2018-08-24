package com.space.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_company")
public class Company {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 255)
    private String account;

    @Column(length = 255)
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
