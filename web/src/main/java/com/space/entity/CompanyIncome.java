package com.space.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_company_income")
public class CompanyIncome {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 32)
    private String date;

    private double dayIncome;

    @Column(length = 32)
    private String projectID;

    @Column(length = 32)
    private String companyID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(double dayIncome) {
        this.dayIncome = dayIncome;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
