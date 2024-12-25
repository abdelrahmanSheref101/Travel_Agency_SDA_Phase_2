package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.User;

public class CheckingDate implements Checking{
    User user;
    Event event;
    public CheckingDate(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    @Override
    public boolean check() {
        return event.getStartDate().isBefore(user.getEnd_of_Booking()) && event.getEndDate().isBefore(user.getEnd_of_Booking());
    }
}
