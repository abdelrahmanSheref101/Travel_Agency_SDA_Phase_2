package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserService
{
    final Map<Long, User> users = new HashMap<>();



    public Map<Long, User> getUsers() {
        return users;
    }
    public void setUsers(User user) {
        users.put(user.getId(), user);
    }

}
