package ru.demo.kafka_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.kafka_demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
