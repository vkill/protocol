package com.space.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/index","/"})
public class MainController {

    @RequestMapping("/helloWorld")
    public String index() {
        return "666666";
    }
}
