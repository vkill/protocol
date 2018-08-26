package com.space.controller;

import com.space.entity.OrderType;
import com.space.service.OrderTypeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orderType")
public class OrderTypeController {

    @Resource
    OrderTypeService orderTypeService;

    @RequestMapping("/getTypeByName")
    public Map getOrderTypeByProject(@RequestBody Map data) {
        Map map = new HashMap();
//        System.out.println(data.get("projectName"));,
        String projectName = (String) data.get("projectName");
        List<OrderType> result = orderTypeService.getOrderTypeByPro(projectName);
        if (result.size() > 0) {
            map.put("success", true);
            map.put("msg","成功获取数据列表");
            map.put("data", result);
        } else {
            // 没项目
            map.put("success",false);
            map.put("msg","此栏目没开设任何操作");
        }

        return map;
    }
}
