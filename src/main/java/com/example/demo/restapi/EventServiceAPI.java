package com.example.demo.restapi;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/travelAgency/event")
public class EventServiceAPI {
    public static EventService eventService;
    public Recommendation recommendation;


    public EventServiceAPI(EventService eventService, Recommendation recommendation) {
        EventServiceAPI.eventService = eventService;
        this.recommendation = recommendation;


    }

    @PostMapping("/addEvent")
    public Event eventAdded(@RequestParam String name, @RequestParam String description, @RequestParam String location, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,@RequestParam double ticketPrice) {
        Event event = new Event(name, description, location, startDate, endDate, ticketPrice);
        AddingEvent addingEvent = new AddingEvent(event,eventService);
        if(addingEvent.add())
        {
            recommendation.recommend(event);

            return event;
        }
        return null;
    }


    @PostMapping("/exist")
    public String checkEvent(@RequestParam String name) {
        Event event = new Event(name);
        String msg = "this event not in system";
        CheckingEvent checkingEvent = new CheckingEvent(event,eventService);
        if(checkingEvent.check())
        {
            msg = "this event is exist";
        }
        return msg;
    }

    @GetMapping("/AllEvents")
    public Map<Long,Event> getAllEvents()
    {
        return eventService.getEvents();
    }

    @GetMapping("/Recommend")
    public List<String> getRecommendation() {
        return recommendation.getLog();

    }



}
