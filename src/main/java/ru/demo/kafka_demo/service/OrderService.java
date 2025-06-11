package ru.demo.kafka_demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.demo.kafka_demo.model.Order;
import ru.demo.kafka_demo.repository.OrderRepository;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderKafkaProducer orderKafkaProducer;
    private final OrderRepository orderRepository;

    public OrderService(OrderKafkaProducer orderKafkaProducer, OrderRepository orderRepository) {
        this.orderKafkaProducer = orderKafkaProducer;
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        // saving order
        orderRepository.save(order);
        // send to kafka
        orderKafkaProducer.sendOrderToKafka(order);
        log.info("Save order: {}", order);
    }
}
