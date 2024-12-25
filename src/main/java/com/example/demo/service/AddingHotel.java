package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


public class AddingHotel  implements Adding{

    private Hotel hotel;
    private HotelService hotelService;
    private static long idCounter = 1;


    public AddingHotel(Hotel hotel, HotelService hotelService) {
        this.hotel = hotel;
        this.hotelService = hotelService;
    }


    @Override
    public boolean add() {
        for(Map.Entry<Long, Hotel> entry:hotelService.hotels.entrySet()){
            Hotel tempHotel = entry.getValue();
            if(tempHotel.getEmail().equals(hotel.getEmail())){
                return false;
            }
        }
        hotel.setId(idCounter++);
        hotelService.setHotel(hotel);
        return true;
    }
}
