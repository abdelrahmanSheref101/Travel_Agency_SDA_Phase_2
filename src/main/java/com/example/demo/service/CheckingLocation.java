package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.User;

public class CheckingLocation implements Checking{

    User user;
    Event event;
    public CheckingLocation(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    @Override
    public boolean check() {
        return user.getLocation().equals(event.getLocation());
    }
}
