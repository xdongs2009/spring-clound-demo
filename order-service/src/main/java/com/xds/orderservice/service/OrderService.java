package com.xds.orderservice.service;

import com.xds.orderservice.entity.Order;
import com.xds.orderservice.entity.OrderSaveEvent;

public interface OrderService {

    long save(Order order);

    long saveEvent(OrderSaveEvent event);

    void saveOrderAndEvent(String remark);

    void newOrderSaveEventHandle() throws Exception;
}
