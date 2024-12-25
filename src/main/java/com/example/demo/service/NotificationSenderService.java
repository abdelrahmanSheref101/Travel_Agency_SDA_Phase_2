package com.example.demo.service;

import com.example.demo.model.NotificationTemplate;
import com.example.demo.model.NotificationQueueItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationSenderService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private NotificationQueueService notificationQueueService;


    public void sendNotification(String toEmail, NotificationTemplate notificationTemplate, Map<String, String> placeholders,String ForWhat) {
        String content = notificationTemplate.generateContent(placeholders);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(notificationTemplate.getSubject());
        message.setText(content);


        new Thread(() -> { // Send the email asynchronously
            try {
                emailSender.send(message);
                System.out.println("Email sent to: " + toEmail+" "+ForWhat);
            } catch (Exception e) {
                System.out.println("Failed to send email to: " + toEmail);
            }
        }).start();
    }

    public void processQueue() {
        while (!notificationQueueService.isEmpty()) {
            NotificationQueueItem item = notificationQueueService.dequeue();
            sendNotification(item.getEmail(), item.getNotificationTemplate(), item.getPlaceholders(),item.getForWhat());
        }
    }
}
