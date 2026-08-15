package com.fooddelivery.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    private final List<Map<String, Object>> history = new ArrayList<>();

    @PostMapping("/email")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, Object> request) {
        // TODO: integrate a real email provider (e.g. JavaMailSender) or just log + store for the demo
        Map<String, Object> log = Map.of("type", "EMAIL", "to", request.get("to"), "status", "SENT_TODO");
        history.add(log);
        return ResponseEntity.ok(log);
    }

    @PostMapping("/sms")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody Map<String, Object> request) {
        // TODO: integrate a real/mock SMS provider
        Map<String, Object> log = Map.of("type", "SMS", "to", request.get("to"), "status", "SENT_TODO");
        history.add(log);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Map<String, Object>>> history() {
        return ResponseEntity.ok(history);
    }
}
