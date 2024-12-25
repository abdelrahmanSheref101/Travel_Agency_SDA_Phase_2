package com.example.demo.service;

import com.example.demo.model.NotificationTemplate;
import com.example.demo.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Service
public class ThreadOfRegistertion implements Runnable {

    private final UserService userService;
    private final NotificationManager notificationManager;

    private final Set<Long> processedUsers = new HashSet<>();
    private final NotificationTemplate registrationTemplate;

    private volatile boolean isProcessing = false; // Prevent overlapping


    public ThreadOfRegistertion(UserService userService, NotificationManager notificationManager) {
        this.registrationTemplate = new NotificationTemplate(
                "Registration_confirmation",
                "Registration Confirmation",
                "Dear {name}, your registration to {system} is confirmed. Thanks for using our service!"
        );
        this.userService = userService;
        this.notificationManager = notificationManager;
    }

    @PostConstruct
    public void startThread() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {

                if (!isProcessing) {
                    synchronized (this) {
                        isProcessing = true;

                        for (Map.Entry<Long, User> entry : userService.getUsers().entrySet()) {
                            User user = entry.getValue();
                            if (!processedUsers.contains(user.getId())) {
                                processedUsers.add(user.getId());


                                Map<String, String> placeholders = Map.of(
                                        "name", user.getName(),
                                        "system", "Travel Agency"
                                );


                                notificationManager.enqueueNotification(
                                        registrationTemplate,
                                        user.getEmail(),
                                        placeholders,
                                        "Registration Confirmation"
                                );
                            }
                        }


                        notificationManager.processQueue();

                        isProcessing = false;
                    }
                }


                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            } catch (Exception e) {
                isProcessing = false;
                e.printStackTrace();
            }
        }
    }
}
