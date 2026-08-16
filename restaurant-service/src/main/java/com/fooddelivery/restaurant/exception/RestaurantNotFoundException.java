package com.fooddelivery.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(String id) {
        super("Restaurant not found with id: " + id);
    }
}
