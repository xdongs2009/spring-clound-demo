package com.xds.feginservice.service;


import org.springframework.stereotype.Component;

@Component
public class HystricCall implements FeginService {
    @Override
    public String sayHiService(String name) {
        return "[fegin-service]-sorry,"+name+", hystric.";
    }
}
