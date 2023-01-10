package com.erdemnayin.edaorderservice.controller;

import com.erdemnayin.edabasedomains.dto.Order;
import com.erdemnayin.edabasedomains.dto.OrderEvent;
import com.erdemnayin.edaorderservice.service.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderProducer orderProducer;
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        order.setId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is in pending state.");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return "Order taken successfully.";
    }
}
