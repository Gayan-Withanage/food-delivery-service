package com.fooddelivery.order.controller;

import com.fooddelivery.order.model.Order;
import com.fooddelivery.order.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable String id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        order.setStatus("CREATED");
        Order saved = orderRepository.save(order);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestBody Map<String, Object> checkoutRequest) {
        String orderId = String.valueOf(checkoutRequest.get("orderId"));
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Order not found"));
        }
        Order order = orderOpt.get();
        order.setStatus("CHECKED_OUT");
        orderRepository.save(order);
        // TODO: call Payment Service and Delivery Service here with WebClient
        return ResponseEntity.ok(Map.of("status", "CHECKED_OUT", "orderId", orderId));
    }
}