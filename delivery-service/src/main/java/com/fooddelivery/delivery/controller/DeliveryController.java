package com.fooddelivery.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final Map<String, Map<String, Object>> deliveries = new HashMap<>();

    @GetMapping
    public ResponseEntity<Collection<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(deliveries.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        return ResponseEntity.ok(deliveries.getOrDefault(id, Map.of("id", id)));
    }

    @PostMapping("/assign")
    public ResponseEntity<Map<String, Object>> assign(@RequestBody Map<String, Object> request) {
        // TODO: pick an available rider, persist assignment
        String id = UUID.randomUUID().toString();
        Map<String, Object> delivery = new HashMap<>(request);
        delivery.put("id", id);
        delivery.put("status", "ASSIGNED");
        deliveries.put(id, delivery);
        return ResponseEntity.status(201).body(delivery);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable String id, @RequestBody Map<String, Object> body) {
        // TODO: update delivery status (PICKED_UP, ON_THE_WAY, DELIVERED), notify Notification Service
        Map<String, Object> delivery = deliveries.getOrDefault(id, new HashMap<>());
        delivery.put("status", body.get("status"));
        deliveries.put(id, delivery);
        return ResponseEntity.ok(delivery);
    }
}
