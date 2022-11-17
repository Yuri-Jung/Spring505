package com.bitc.java505.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() throws Exception{
        return "hello world! Spring boot test project with inteliJ!! ";
    }

}
