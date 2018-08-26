package com.space.controller;

import com.space.entity.CompanyIncome;
import com.space.service.CompanyIncomeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companyIncome")
public class CompanyIncomeController {

    @Resource
    CompanyIncomeService companyIncomeService;

    @RequestMapping("/getIncome")
    public Map getCompanyIncome() {
        Map result = new HashMap();
        List<CompanyIncome> incomeList = companyIncomeService.getIncomeList("company1");
        if (incomeList.size() == 0) {
            result.put("success",false);
            result.put("message","没有此资料");
        } else {
            result.put("success", true);
            result.put("data", incomeList);
        }
        return result;
    }
}
