package com.fooddelivery.notification.repository;

import com.fooddelivery.notification.model.NotificationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<NotificationLog, String> {
}