package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookingHotel implements Booking {
    final Map<Hotel, List<User>> DataOfBooking = new HashMap<>();
    final List<User> listOfUsers = new ArrayList<>();

    @Autowired
    public UserService userService;
    @Autowired
    public HotelService hotelService;

   public User user;
   public Hotel hotel;
   public Room room;
   public LocalDate date;


       public BookingHotel(UserService userService, HotelService hotelService) {
           this.userService = userService;
           this.hotelService = hotelService;
        }


    public Map<Hotel, List<User>> getDataOfBooking() {
        return DataOfBooking;
    }

    @Override
    public String book() {
        String msg =  "";
        AuthenticationUser authenticationUser = new AuthenticationUser(user,userService);
        CheckingHotel checkingHotel = new CheckingHotel(hotel,hotelService);
        SearchingForRoom searchingForRoom = new SearchingForRoom(room,hotel,hotelService);
        Hotel avaliable;
        avaliable = searchingForRoom.search();
        if(!authenticationUser.check())
        {
            msg = "You are not authorized to perform this action";
        }
        else if(!checkingHotel.check())
        {
            msg = "this hotel is not in system";
        }
        else if(avaliable != null)
        {
            user.setEnd_of_Booking(date);
            user.setLocation(hotel.getLocation());
            user.setTypeOfRoom(room);
            listOfUsers.add(user);
            hotel.minusRoom(room);
            hotelService.hotels.put(hotel.getId(),hotel);
            DataOfBooking.put(hotel,listOfUsers);
            msg = "Hotel Booked";

        }
        return msg;


    }
}
