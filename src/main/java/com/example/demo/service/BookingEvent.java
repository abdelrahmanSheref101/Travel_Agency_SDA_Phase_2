package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookingEvent implements Booking {
    final Map<Event, List<User>> DataOfBooking = new HashMap<>();
    final List<User> listOfUsers = new ArrayList<>();

    @Autowired
    public UserService userService;
    @Autowired
    public EventService eventService;

    public User user;

    public Event event;
    public LocalDate date;


    public BookingEvent(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }


    public Map<Event, List<User>> getDataOfBooking() {
        return DataOfBooking;
    }

    @Override
    public String book() {
        String msg =  "";
        AuthenticationUser authenticationUser = new AuthenticationUser(user,userService);
        CheckingEvent checkingEvent = new CheckingEvent(event,eventService);

        if(!authenticationUser.check())
        {
            msg = "You are not authorized to perform this action";
        }
        else if(!checkingEvent.check())
        {
            msg = "this event is not in system";
        }
        else
        {

            listOfUsers.add(user);

            eventService.events.put(event.getId(),event);
            DataOfBooking.put(event,listOfUsers);
            msg = "Event Booked";

        }
        return msg;


    }
}
