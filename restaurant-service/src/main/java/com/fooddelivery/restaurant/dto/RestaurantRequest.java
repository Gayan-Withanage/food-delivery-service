package com.fooddelivery.restaurant.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

/**
 * What a client sends in the body of POST /api/restaurants.
 * Kept separate from the Restaurant document so we can validate
 * incoming data without exposing internal fields like "id" or "menu".
 */
public class RestaurantRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cuisine is required")
    private String cuisine;

    @NotBlank(message = "address is required")
    private String address;

    @DecimalMin(value = "0.0", message = "rating must be >= 0")
    @DecimalMax(value = "5.0", message = "rating must be <= 5")
    private double rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
