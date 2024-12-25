package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public class RegistertionUser implements Adding{

    User user;
    UserService userService;
    private static long idCounter = 1;
    public RegistertionUser(User user, UserService userService) {
        this.user = user;
        this.userService = userService;

    }


    @Override
    public boolean add() {
        for(Map.Entry<Long, User> entry:userService.users.entrySet()){
            User tempUser = entry.getValue();
            if(tempUser.getEmail().equals(user.getEmail())){
                return false;
            }
        }
        user.setId(idCounter++);
        userService.setUsers(user);
        return true;

    }
}
