package com.xds.userservice.schedule;

import com.xds.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderTask {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTask.class);

    @Scheduled(cron = "*/10 * * * * ?")
    public void newUserSaveEventHandle() {
        LOGGER.info(new Date().toString());
        try {
            userService.newUserSaveEventHandle();
        } catch (Exception e) {
            LOGGER.info("inner error");
        }
    }
}
