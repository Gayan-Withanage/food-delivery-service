package com.fooddelivery.restaurant.repository;

import com.fooddelivery.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
