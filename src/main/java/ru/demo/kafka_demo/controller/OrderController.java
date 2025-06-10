package ru.demo.kafka_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.demo.kafka_demo.model.Order;
import ru.demo.kafka_demo.service.OrderService;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final AtomicInteger orderIdCounter;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        this.orderIdCounter = new AtomicInteger();
    }

    @GetMapping
    public String getOrders() {
        return orderIdCounter.get() + "";
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        log.info("Create order called: order={}", order);
        int orderId = orderIdCounter.incrementAndGet();
        var productName = order.productName() + ThreadLocalRandom.current().nextInt(100);
        var orderToSave = new Order(Integer.toString(orderId), productName);
        orderService.save(orderToSave);
    }
}
