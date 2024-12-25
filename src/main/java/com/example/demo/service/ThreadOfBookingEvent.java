package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Hotel;
import com.example.demo.model.NotificationTemplate;
import com.example.demo.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Service
public class ThreadOfBookingEvent implements Runnable {
    private final BookingEvent bookingEvent;
    private final NotificationManager notificationManager;

    private final Set<String> processedUsers = new HashSet<>();
    private final NotificationTemplate BookingHotelTemplate;

    private volatile boolean isProcessing = false; // Prevent overlapping

    public ThreadOfBookingEvent(BookingEvent bookingEvent, NotificationManager notificationManager) {
        this.BookingHotelTemplate = new NotificationTemplate(
                "Booking_Event_Confirmation",
                "Booking Event Confirmation",
                "Dear {name}, your booking of {EventName} is confirmed. Thanks for using our service!"
        );
        this.bookingEvent = bookingEvent;
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

                        for (Map.Entry<Event, List<User>> entry : bookingEvent.getDataOfBooking().entrySet()) {
                            Event event = entry.getKey();
                            List<User> users = entry.getValue();

                            for (User user : users) {
                                if (!processedUsers.contains(user.getEmail())) {
                                    processedUsers.add(user.getEmail());


                                    Map<String, String> placeholders = Map.of(
                                            "name", user.getName(),
                                            "EventName", event.getName()
                                    );


                                    notificationManager.enqueueNotification(
                                            BookingHotelTemplate,
                                            user.getEmail(),
                                            placeholders,
                                            "Booking Event Confirmation"
                                    );
                                }
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
