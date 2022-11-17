package com.bitc.java505.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hi")
    public String hello() throws Exception {
        return "Hello World, String Boot Test Project!!yeah";
    }
}
