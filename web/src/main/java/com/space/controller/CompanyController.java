package com.space.controller;

import com.space.entity.Company;
import com.space.service.CompanyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Resource
    CompanyService companyService;

    @RequestMapping("/login")
    public Map companyLogin(@RequestBody Map map) {
        System.out.println(map);
        Map result = new HashMap();

        String account = (String) map.get("account");
        String password = (String) map.get("password");

        Company login = companyService.login(account, password);
        if (login != null) {
            result.put("success", true);
            result.put("account",login);
        } else {
            result.put("success", false);
        }


        return result;
    }
}
