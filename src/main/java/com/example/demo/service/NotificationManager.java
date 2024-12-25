package com.example.demo.service;

import com.example.demo.model.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationManager {

    @Autowired
    private NotificationQueueService notificationQueueService;

    @Autowired
    private NotificationSenderService notificationSenderService;

    @Autowired
    private NotificationStatisticsService notificationStatisticsService;


    public void enqueueNotification(NotificationTemplate template, String email, Map<String, String> placeholders,String ForWhat) {
        notificationQueueService.addToQueue(template, email, placeholders, ForWhat);
    }


    public void processQueue() {
        notificationSenderService.processQueue();
    }


    public void logSuccess() {
        notificationStatisticsService.logSuccessfulSend();
    }

    public void logFailure() {
        notificationStatisticsService.logFailedSend();
    }

    public void printStatistics() {
        notificationStatisticsService.printStatistics();
    }
}

