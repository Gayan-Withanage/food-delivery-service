package com.fooddelivery.restaurant.controller;

import com.fooddelivery.restaurant.model.Restaurant;
import com.fooddelivery.restaurant.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "ApiKeyAuth")
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable String id) {
        return restaurantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant saved = restaurantRepository.save(restaurant);
        return ResponseEntity.status(201).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!restaurantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        restaurantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}