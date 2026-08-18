package com.fooddelivery.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final List<Map<String, Object>> history = new ArrayList<>();

    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> process(@RequestBody Map<String, Object> request) {
        // TODO: integrate a real/mock payment provider, persist to MongoDB
        Map<String, Object> payment = new HashMap<>(request);
        payment.put("id", UUID.randomUUID().toString());
        payment.put("status", "SUCCESS_TODO");
        history.add(payment);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Map<String, Object>>> history() {
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
        return history.stream().filter(p -> id.equals(p.get("id")))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
