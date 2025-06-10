package ru.demo.kafka_demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.demo.kafka_demo.model.Order;

@Component
public class OrderKafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderKafkaProducer.class);
    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    public OrderKafkaProducer(KafkaTemplate<String, Order> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public void sendOrderToKafka(Order order) {
        orderKafkaTemplate.send("orders", order.orderId(), order);
        log.info("Order send to kafka: id={}", order.orderId());
    }
}
