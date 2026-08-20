package com.fooddelivery.delivery.repository;

import com.fooddelivery.delivery.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
}