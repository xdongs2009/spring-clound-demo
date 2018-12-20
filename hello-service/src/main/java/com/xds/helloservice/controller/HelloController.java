package com.xds.helloservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${server.port}")
    private String port;


//    @Value("${money}")
//    private String money;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "lucy") String name) {
        return "[hello-service]-hi " + name + " ,i am from port:" + port;
    }

//    @RequestMapping("/money")
//    public String getMoney() {
//        return "[hello-service]-" + money + "left";
//    }
}
