package com.xds.feginservice.service;


import org.springframework.stereotype.Component;

@Component
public class HystricCall implements FeginService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,"+name+", hystric.";
    }
}
