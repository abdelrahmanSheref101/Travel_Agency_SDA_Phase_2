package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.User;

import java.util.Map;

public class CheckingEvent implements Checking{
    Event event;
    EventService eventService;
    public CheckingEvent(Event event, EventService eventService) {
        this.event = event;
        this.eventService = eventService;
    }


    @Override
    public boolean check() {
        for(Map.Entry<Long, Event> entry:eventService.events.entrySet())
        {
            Event tempEvent = entry.getValue();
            if(event.getName().equals(tempEvent.getName()))
            {
                return true;
            }
        }
        return false;
    }
}
