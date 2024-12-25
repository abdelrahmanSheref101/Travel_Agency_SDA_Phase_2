package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationStatisticsService {

    private int successfulSends = 0;
    private int failedSends = 0;


    public void logSuccessfulSend() {
        successfulSends++;
    }


    public void logFailedSend() {
        failedSends++;
    }


    public void printStatistics() {
        System.out.println("Successful Sends: " + successfulSends);
        System.out.println("Failed Sends: " + failedSends);
    }
}
