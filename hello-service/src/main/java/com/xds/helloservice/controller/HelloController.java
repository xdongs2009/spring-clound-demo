package com.xds.helloservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HelloController {

    @Value("${server.port}")
    private String port;


//    @Value("${money}")
//    private String money;

    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "lucy") String name) {
        return "[hello-service]-hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name) {
        return "[hello-service]-hi,"+name+",sorry,error!";
    }

//    @RequestMapping("/money")
//    public String getMoney() {
//        return "[hello-service]-" + money + "left";
//    }


    @HystrixCommand(fallbackMethod = "hi1Error")
    @RequestMapping("/hi1")
    public String hi1(@RequestParam(value = "name", defaultValue = "lucy") String name) {
        return "[hello-service]-hi " + name + " ,i am from port:" + "aaa";
    }

    public String hi1Error(String name) {
        return "[hello-service]-hi,"+name+",sorry,error!";
    }
}
