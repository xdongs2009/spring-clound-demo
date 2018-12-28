package com.xds.orderservice.repository;

import com.xds.orderservice.entity.OrderSaveEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSaveEventRepositroy extends JpaRepository<OrderSaveEvent, Long> {
    List<OrderSaveEvent> findByStatus(int status);
}
