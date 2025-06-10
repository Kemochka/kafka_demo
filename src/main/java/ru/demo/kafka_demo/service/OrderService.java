package ru.demo.kafka_demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.demo.kafka_demo.model.Order;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderKafkaProducer orderKafkaProducer;

    public OrderService(OrderKafkaProducer orderKafkaProducer) {
        this.orderKafkaProducer = orderKafkaProducer;
    }

    public void save(Order order) {
        // send kafka
        orderKafkaProducer.sendOrderToKafka(order);
        // saving to database
        log.info("Save order: {}", order);
    }
}
