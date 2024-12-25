package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class AuthenticationUser  implements Checking{
     User user;
     UserService userService;
    public AuthenticationUser(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }


    @Override
    public boolean check() {
       for(Map.Entry<Long, User> entry:userService.users.entrySet())
       {
           User tempUser = entry.getValue();
           if(tempUser.getEmail().equals(user.getEmail()))
           {
               return true;
           }
       }
       return false;
    }
}
