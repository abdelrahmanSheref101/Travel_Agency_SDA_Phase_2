package com.example.demo.service;

import com.example.demo.model.Hotel;

import java.util.Map;


public class SearchingHotelByName implements Searching {
    private  String hotelName;

    private  HotelService hotelService;

    public Hotel hotelOfSameName;

    public SearchingHotelByName(String hotelName, HotelService hotelService) {
        this.hotelName = hotelName;
        this.hotelService = hotelService;
    }


    @Override
    public Hotel search() {
       for(Map.Entry<Long, Hotel> hotelEntry:hotelService.hotels.entrySet())
       {

           Hotel curHotel = hotelEntry.getValue();
           if(hotelName.equalsIgnoreCase(curHotel.getName()))
           {
               hotelOfSameName = curHotel;
               break;
           }
       }
        return hotelOfSameName;
    }
}
