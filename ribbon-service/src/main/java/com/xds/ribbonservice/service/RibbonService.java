package com.xds.ribbonservice.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiServiceFall")
    public String hiService(String name) {
        String result = restTemplate.getForObject("http://HELLO-SERVICE/hi?name=" + name, String.class);
        return "[ribbon-service]-" + result;
    }

    public String hiServiceFall(String name) {
        return "[ribbon-service]-sorry," + name + ". hystrix";
    }

}
