package com.fooddelivery.notification.controller;

import com.fooddelivery.notification.model.NotificationLog;
import com.fooddelivery.notification.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/email")
    public ResponseEntity<NotificationLog> sendEmail(@RequestBody Map<String, Object> request) {
        NotificationLog log = new NotificationLog("EMAIL", String.valueOf(request.get("to")),
                String.valueOf(request.getOrDefault("message", "")), "SENT");
        return ResponseEntity.ok(notificationRepository.save(log));
    }

    @PostMapping("/sms")
    public ResponseEntity<NotificationLog> sendSms(@RequestBody Map<String, Object> request) {
        NotificationLog log = new NotificationLog("SMS", String.valueOf(request.get("to")),
                String.valueOf(request.getOrDefault("message", "")), "SENT");
        return ResponseEntity.ok(notificationRepository.save(log));
    }

    @GetMapping("/history")
    public ResponseEntity<List<NotificationLog>> history() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }
}