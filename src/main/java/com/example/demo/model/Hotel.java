package com.example.demo.model;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Hotel {
    private Long id;
    private String name;
    private String location;
    private String email;
    private Integer numberOfRooms;
    private double pricePerDay;
    public Map<Room, Integer>typesOfRooms;

    public Hotel() {
    }

    public Hotel(String name,
                 String location,
                 String email,
                 Integer numberOfRooms,
                 double pricePerDay) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.numberOfRooms = numberOfRooms;
        this.pricePerDay = pricePerDay;
    }

    public Hotel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Hotel(String name, String location, String email, Integer numberOfRooms, double pricePerDay, Map<Room, Integer> typesOfRooms) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.numberOfRooms = numberOfRooms;
        this.pricePerDay = pricePerDay;
        this.typesOfRooms = typesOfRooms;
    }

    public Map<Room, Integer> getTypesOfRooms() {
        return typesOfRooms;
    }

    public void setTypesOfRooms(Map<Room, Integer> typesOfRooms) {
        this.typesOfRooms = typesOfRooms;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    public void minusRoom(Room room) {
        typesOfRooms.put(room, typesOfRooms.get(room) - 1);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
