package com.xds.feginservice.controller;

import com.xds.feginservice.service.FeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeginController {

    @Autowired
    private FeginService feginService;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        String result = feginService.sayHiService(name);
        return "[fegin-service]" + result;
    }
}
