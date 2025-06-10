package ru.demo.kafka_demo.model;

public record Order(
        String orderId,
        String productName
) {

}
