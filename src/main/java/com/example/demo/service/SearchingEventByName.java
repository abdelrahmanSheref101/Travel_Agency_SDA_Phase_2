package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Hotel;

import java.util.Map;


public class SearchingEventByName implements Searching {
    private  String eventName;

    private  EventService eventService;

    public Event eventOfSameName;

    public SearchingEventByName(String eventName, EventService eventService) {
        this.eventName = eventName;
        this.eventService = eventService;
    }


    @Override
    public Event search() {
        for(Map.Entry<Long, Event> eventEntry:eventService.events.entrySet())
        {

            Event curEvent = eventEntry.getValue();
            if(eventName.equalsIgnoreCase(curEvent.getName()))
            {
                eventOfSameName = curEvent;
                break;
            }
        }
            return eventOfSameName;
    }
}
