package com.example.demo.service;

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
public class ThreadOfBookingHotel implements Runnable {
    private final BookingHotel bookingHotel;
    private final NotificationManager notificationManager;

    private final Set<String> processedUsers = new HashSet<>();
    private final NotificationTemplate BookingHotelTemplate;

    private volatile boolean isProcessing = false; // Prevent overlapping

    public ThreadOfBookingHotel(BookingHotel bookingHotel, NotificationManager notificationManager) {
        this.BookingHotelTemplate = new NotificationTemplate(
                "Booking_Hotel_Confirmation",
                "Booking Hotel Confirmation",
                "Dear {name}, your booking of {hotelName} is confirmed. Thanks for using our service!"
        );
        this.bookingHotel = bookingHotel;
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

                        for (Map.Entry<Hotel, List<User>> entry : bookingHotel.getDataOfBooking().entrySet()) {
                            Hotel hotel = entry.getKey();
                            List<User> users = entry.getValue();

                            for (User user : users) {
                                if (!processedUsers.contains(user.getEmail())) {
                                    processedUsers.add(user.getEmail());


                                    Map<String, String> placeholders = Map.of(
                                            "name", user.getName(),
                                            "hotelName", hotel.getName()
                                    );


                                    notificationManager.enqueueNotification(
                                            BookingHotelTemplate,
                                            user.getEmail(),
                                            placeholders,
                                            "Booking Hotel Confirmation"
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
