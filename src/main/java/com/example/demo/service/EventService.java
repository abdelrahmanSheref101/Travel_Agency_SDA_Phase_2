package com.example.demo.service;

import com.example.demo.model.Event;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class EventService {
    final Map<Long, Event> events = new HashMap<>();


    public Map<Long, Event> getEvents() {
        return events;
    }
    public void setEvents(Event event) {
        events.put(event.getId(), event);
    }

}
