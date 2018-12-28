package com.xds.orderservice.schedule;

import com.xds.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderTask {

    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTask.class);

    @Scheduled(cron = "*/10 * * * * ?")
    public void newOrderSaveEventHandle() {
        LOGGER.info(new Date().toString());
        try {
            orderService.newOrderSaveEventHandle();
        } catch (Exception e) {
            LOGGER.info("error happen,message = " + e.getMessage());
        }
    }
}
