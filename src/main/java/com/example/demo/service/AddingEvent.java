package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.User;

import java.util.Map;

public class AddingEvent implements Adding{

    Event event;
    EventService eventService;


    private static long idCounter = 1;
    public AddingEvent(Event event, EventService eventService) {
        this.event = event;
        this.eventService = eventService;


    }


    @Override
    public boolean add() {
        for(Map.Entry<Long, Event> entry:eventService.events.entrySet()){
            Event tempevent = entry.getValue();
            if(!event.getName().equals(tempevent.getName())){

                return false;
            }
        }
        event.setId(idCounter++);
        eventService.setEvents(event);


        return true;

    }


}
