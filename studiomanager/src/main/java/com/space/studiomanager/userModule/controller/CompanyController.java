package com.space.studiomanager.userModule.controller;


import com.space.studiomanager.entity.Company;
import com.space.studiomanager.statusModule.WebStatus;
import com.space.studiomanager.userModule.service.CompanyService;
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
    public Map companyLogin(String username, String password) {
        Map result = new HashMap();

        Company login = companyService.login(username, password);
//        if (login != null) {
//            result.put("success", true);
//            result.put("account",login);
//        } else {
//            result.put("success", false);
//        }
        if (login != null) {
            result.put("status", WebStatus.OK);
            result.put("statusText", WebStatus.OK_TEXT);
            result.put("currentAuthority", "user");
            Map profile = new HashMap();
            String name = login.getAccount();
            String department = "抖音";
            String avatar = "https://img.alicdn.com/tfs/TB1L6tBXQyWBuNjy0FpXXassXXa-80-80.png";
            int userid = login.getId();
            profile.put("name", name);
            profile.put("department", department);
            profile.put("avatar", avatar);
            profile.put("userid", userid);
            result.put("profile", profile);
        } else {
            result.put("status", 401);
            result.put("statusText", "unauthorized");
            result.put("currentAuthority","guest");
        }

        return result;
    }
}
