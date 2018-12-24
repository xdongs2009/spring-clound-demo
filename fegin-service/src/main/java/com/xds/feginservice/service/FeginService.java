package com.xds.feginservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hello-service",fallback = HystricCall.class)
public interface FeginService {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiService(@RequestParam(value = "name") String name);
}
