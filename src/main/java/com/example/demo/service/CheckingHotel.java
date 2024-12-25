package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class CheckingHotel implements Checking {
    Hotel hotel;
    HotelService hotelService;
    public CheckingHotel(Hotel hotel, HotelService hotelService) {
        this.hotel = hotel;
        this.hotelService = hotelService;
    }

    @Override
    public boolean check() {
        for(Map.Entry<Long, Hotel> entry:hotelService.hotels.entrySet())
        {
            Hotel tempHotel = entry.getValue();
            if(tempHotel.getEmail().equals(hotel.getEmail()))
            {
                return true;
            }
        }
        return false;
    }
}
