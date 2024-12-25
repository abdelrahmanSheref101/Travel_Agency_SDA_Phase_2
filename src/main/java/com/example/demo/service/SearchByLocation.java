package com.example.demo.service;

import com.example.demo.model.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchByLocation implements Searching{

    private final String hotelLocation;

    public Hotel hotelOfSameLocation;
    public HotelService hotelService;
    public SearchByLocation(String hotelLocation, HotelService hotelService) {
        this.hotelLocation = hotelLocation;
        this.hotelService = hotelService;
    }
    @Override
    public Hotel search() {

        for(Map.Entry<Long, Hotel> hotelEntry:hotelService.hotels.entrySet())
        {

            Hotel curHotel = hotelEntry.getValue();
            if(hotelLocation.equalsIgnoreCase(curHotel.getLocation()))
            {
                hotelOfSameLocation = curHotel;
            }
        }
        return hotelOfSameLocation;
    }
}
