package com.fooddelivery.order.repository;

import com.fooddelivery.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}