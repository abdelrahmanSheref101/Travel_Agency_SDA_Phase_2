package com.example.demo.service;

import com.example.demo.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class HotelService
{
     public final Map<Long, Hotel> hotels = new HashMap<>();

     public void setHotel(Hotel hotel)
     {
         hotels.put(hotel.getId(), hotel);
     }

    public Map<Long, Hotel> getHotels() {
        return hotels;
    }
}
