package com.xds.orderservice.service.impl;

import com.xds.orderservice.entity.Order;
import com.xds.orderservice.entity.OrderSaveEvent;
import com.xds.orderservice.kafka.KafkaSender;
import com.xds.orderservice.repository.OrderRepository;
import com.xds.orderservice.repository.OrderSaveEventRepositroy;
import com.xds.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderSaveEventRepositroy orderSaveEventRepositroy;

    @Autowired
    private KafkaSender kafkaSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Transactional
    public long save(Order order) {
        Order result = orderRepository.save(order);
        return result.getId();
    }


    @Override
    @Transactional
    public long saveEvent(OrderSaveEvent event) {
        OrderSaveEvent result = orderSaveEventRepositroy.save(event);
        return result.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderAndEvent(String remark) {
        //save order
        Order order = new Order();
        order.setRemark(remark);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Order result = orderRepository.save(order);
        //save event
        OrderSaveEvent event = new OrderSaveEvent();
        event.setOrderId(result.getId());
        event.setCreateTime(new Timestamp(System.currentTimeMillis()));
        orderSaveEventRepositroy.save(event);
//        int i =1/0;
        return;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newOrderSaveEventHandle() throws Exception {
        List<OrderSaveEvent> eventList = orderSaveEventRepositroy.findByStatus(0);
        if (eventList == null || eventList.size() == 0) {
            LOGGER.info("no new event");
            return;
        }
//        int i = 1/0;
        for (OrderSaveEvent event : eventList) {
            boolean success = kafkaSender.sendMessage(event.getOrderId() + "");
            if (!success) {
                throw new Exception("send message error");
            }
            event.setStatus(1);
            event.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        orderSaveEventRepositroy.saveAll(eventList);
    }

}
