package com.example.demo.restapi;

import com.example.demo.model.NotificationTemplate;
import com.example.demo.model.User;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/travelAgency/user")
public class UserServiceAPI {

    public static UserService userService;


    public UserServiceAPI(UserService userService) {
        UserServiceAPI.userService = userService;


    }

    @PostMapping("/register")
    public User userAdded(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String phone) {
        User user = new User(name, email,phone, password);
        RegistertionUser registertionUser  = new RegistertionUser(user,userService);
        if(registertionUser.add())
        {
            return user;
        }
        return null;
    }


    @PostMapping("/authentication")
    public String authenticate(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User user = new User(name, email, password);
        String msg = "this user not in system";
        AuthenticationUser authenticationUser  = new AuthenticationUser(user,userService);
        if(authenticationUser.check())
        {
            msg = "this user is authenticated";
        }
        return msg;
    }

    @GetMapping("/AllUsers")
    public Map<Long,User> getAllUsers()
    {
        return userService.getUsers();
    }





}
