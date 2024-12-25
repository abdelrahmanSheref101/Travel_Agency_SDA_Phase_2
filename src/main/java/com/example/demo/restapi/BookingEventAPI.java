package com.example.demo.restapi;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travelAgency/BookingEvent")
public class BookingEventAPI
{

    @Autowired
    BookingEvent bookingEvent;


    public BookingEventAPI(BookingEvent bookingEvent)
    {
        this.bookingEvent = bookingEvent;



    }



    @PostMapping("/BookEvent")
    public String bookingEvent(@RequestParam String EventName,@RequestParam String emailUser)  {

        SearchingEventByName searchingEventByName = new SearchingEventByName(EventName, bookingEvent.eventService);
        SearchForUser searchForUser = new SearchForUser(bookingEvent.userService, emailUser);
        Event event = searchingEventByName.search();
        User user = searchForUser.search();
        if(user != null && event != null)
        {
            bookingEvent.event = event;
            bookingEvent.user = user;
            return bookingEvent.book();
        }

        return null;





    }


    @GetMapping("/AllBooking")
    public Map<Event, List<User>> getAllBooking()
    {

        return bookingEvent.getDataOfBooking();
    }







}
