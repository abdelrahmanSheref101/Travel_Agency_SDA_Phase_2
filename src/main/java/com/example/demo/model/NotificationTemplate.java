package com.example.demo.model;

import java.util.Map;

public class NotificationTemplate {
    private String id;
    private String subject;
    private String content;

    public NotificationTemplate(String id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }


    public String generateContent(Map<String, String> placeholders) {
        String result = content;
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }


    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
