package com.example.demo.model;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
@Service
public class User
{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String location = "none";
    private String password;
    private LocalDate End_of_Booking = LocalDate.now();
    private Room typeOfRoom;

    public User() {
    }

    public User(String name,
                String email,
                String phone,
                String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Room getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(Room typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getEnd_of_Booking() {
        return End_of_Booking;
    }

    public void setEnd_of_Booking(LocalDate end_of_Booking) {
        End_of_Booking = end_of_Booking;
    }
}
