package com.fooddelivery.restaurant.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    // TODO: replace in-memory list with a MongoDB repository (see WebConfig for the api-key filter that already protects these routes)
    private final List<Map<String, Object>> restaurants = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        // TODO: return all restaurants from MongoDB
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        // TODO: fetch single restaurant by id
        return ResponseEntity.ok(Map.of("id", id, "note", "TODO implement"));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> restaurant) {
        // TODO: validate + save to MongoDB
        restaurants.add(restaurant);
        return ResponseEntity.status(201).body(restaurant);
    }

    @GetMapping("/{id}/menu")
    public ResponseEntity<List<Map<String, Object>>> getMenu(@PathVariable String id) {
        // TODO: return the menu items belonging to this restaurant
        return ResponseEntity.ok(new ArrayList<>());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        // TODO: delete restaurant by id
        return ResponseEntity.noContent().build();
    }
}
