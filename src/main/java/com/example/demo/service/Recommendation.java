package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class Recommendation {
    public UserService userService;
    public EventService eventService;
    List<String> log;
    Recommendation(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
        log = new ArrayList<>();
    }

    public List<String> getLog() {
        return log;
    }
//
//    public void recommend(Event event) {
//
//
//
//
//            for (Map.Entry<Long, User> userEntry : userService.users.entrySet()) {
//
//                User curUser = userEntry.getValue();
//                CheckingLocation checkingLocation = new CheckingLocation(curUser, event);
//                CheckingDate checkingDate = new CheckingDate(curUser, event);
//                if (checkingLocation.check() && checkingDate.check()) {
//                    String message = "Recommend " + event.getName() + " to " + curUser.getName() +
//                            " and this details of this event => " + event.toString();
//                    log.add(message);
//                    System.out.println(message);
//                }
//            }
public void recommend(Event event) {

    for (Map.Entry<Long, User> userEntry : userService.users.entrySet()) {
        User curUser = userEntry.getValue();

        CheckingLocation checkingLocation = new CheckingLocation(curUser, event);
        CheckingDate checkingDate = new CheckingDate(curUser, event);

        if (checkingLocation.check() && checkingDate.check()) {
            String message = "Recommend " + event.getName() + " to " + curUser.getName() +
                    " and this details of this event => " + event.toString();
            log.add(message);

        }
    }


}





}


