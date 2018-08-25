package com.space.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/companyIncome")
public class CompanyIncomeController {

    @RequestMapping("/getIncome")
    public Map getCompanyIncome() {
        Map result = new HashMap();


        return null;
    }
}
