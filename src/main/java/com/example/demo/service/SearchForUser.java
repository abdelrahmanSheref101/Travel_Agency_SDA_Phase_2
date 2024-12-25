package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.model.User;

import java.util.Map;

public class SearchForUser {
    public UserService userService;
    public String email;
    public SearchForUser(UserService userService, String email) {
        this.userService = userService;
        this.email = email;
    }
    public User search() {
        User user = null;
        for(Map.Entry<Long, User> userEntry:userService.users.entrySet())
        {

            User curUser = userEntry.getValue();
            if(email.equals(curUser.getEmail()))
            {
                user = curUser;
            }
        }
        return user;
    }
}
