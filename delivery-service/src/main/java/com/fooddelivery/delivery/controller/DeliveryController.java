package com.fooddelivery.delivery.controller;

import com.fooddelivery.delivery.model.Delivery;
import com.fooddelivery.delivery.repository.DeliveryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;

    public DeliveryController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAll() {
        return ResponseEntity.ok(deliveryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getById(@PathVariable String id) {
        return deliveryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/assign")
    public ResponseEntity<Delivery> assign(@RequestBody Delivery delivery) {
        delivery.setStatus("ASSIGNED");
        Delivery saved = deliveryRepository.save(delivery);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        return deliveryRepository.findById(id)
                .map(d -> {
                    d.setStatus(body.get("status"));
                    return ResponseEntity.ok(deliveryRepository.save(d));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}