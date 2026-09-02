package com.fooddelivery.notification.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class NotificationLog {
    @Id
    private String id;
    private String type;
    private String to;
    private String message;
    private String status;

    public NotificationLog() {}
    public NotificationLog(String type, String to, String message, String status) {
        this.type = type; this.to = to; this.message = message; this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}