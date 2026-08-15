package com.fooddelivery.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    // TODO: replace with a MongoDB repository
    private final Map<String, Map<String, Object>> orders = new HashMap<>();

    @GetMapping
    public ResponseEntity<Collection<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(orders.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        return ResponseEntity.ok(orders.getOrDefault(id, Map.of("id", id, "note", "TODO implement")));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> order) {
        // TODO: validate items, call Restaurant Service to check menu/prices, persist
        String id = UUID.randomUUID().toString();
        order.put("id", id);
        order.put("status", "CREATED");
        orders.put(id, order);
        return ResponseEntity.status(201).body(order);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestBody Map<String, Object> checkoutRequest) {
        // TODO: call Payment Service (/payments/process) then Delivery Service (/delivery/assign)
        return ResponseEntity.ok(Map.of("status", "CHECKOUT_TODO", "request", checkoutRequest));
    }
}
