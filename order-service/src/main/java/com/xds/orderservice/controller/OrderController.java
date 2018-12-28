package com.xds.orderservice.controller;


import com.xds.orderservice.entity.Order;
import com.xds.orderservice.entity.OrderSaveEvent;
import com.xds.orderservice.kafka.KafkaSender;
import com.xds.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaSender kafkaSender;


    @RequestMapping("/order")
    public String saveOrder(@RequestParam(value = "remark", defaultValue = "order remark") String remark) {
        Order order = new Order();
        order.setRemark(remark);
        order.setStatus(1);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        long id = orderService.save(order);
        return "[order-service]-save success. id = " + id;
    }

    @RequestMapping("/event")
    public String saveOrder() {
        OrderSaveEvent event = new OrderSaveEvent();
        event.setOrderId(1L);
        event.setCreateTime(new Timestamp(System.currentTimeMillis()));
        long id = orderService.saveEvent(event);
        return "[order-service]-save event success. id = " + id;
    }

    @RequestMapping("/hi")
    public String hi() {
        return "hello";
    }

    @RequestMapping("/send")
    public String sendMessage(@RequestParam(value = "message", defaultValue = "a message") String message) {
        boolean success = kafkaSender.sendMessage(message);
        if (success) {
            return "send message suceess";
        }
        return "send message fail";
    }

    @RequestMapping("/save")
    public String saveOrderAndEvent(@RequestParam(value = "remark", defaultValue = "order remark") String remark) {
        try {
            orderService.saveOrderAndEvent(remark);
        } catch (Exception e) {
            return "[order-service]-save order && save event failed";
        }
        return "[order-service]-save order && save event  success";
    }


}
