package com.space.protocol.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dy")
public class DouyinController {

    public Map dyPointGood() {
        Map result = new HashMap();

        int count = 10000; // 点赞个数

        String vedioId = "123456789";



        return result;
    }
}
