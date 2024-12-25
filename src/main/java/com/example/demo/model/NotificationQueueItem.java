package com.example.demo.model;

import java.util.Map;

public class NotificationQueueItem {
    private String email;
    private NotificationTemplate notificationTemplate;
    private Map<String, String> placeholders;
    private String ForWhat;

    public NotificationQueueItem(String email, NotificationTemplate notificationTemplate, Map<String, String> placeholders, String ForWhat) {
        this.email = email;
        this.notificationTemplate = notificationTemplate;
        this.placeholders = placeholders;
        this.ForWhat = ForWhat;
    }

    public String getEmail() {
        return email;
    }

    public NotificationTemplate getNotificationTemplate() {
        return notificationTemplate;
    }

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }
    public String getForWhat() {
        return ForWhat;
    }
}
