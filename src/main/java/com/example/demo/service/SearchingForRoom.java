package com.example.demo.service;

import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchingForRoom implements Searching{

    private final Room room;
    private Hotel hotel;

    private HotelService hotelService;

    public Hotel avaliable = null;
    public SearchingForRoom(Room room,Hotel hotel,HotelService hotelService) {

        this.room = room;
        this.hotel = hotel;
        this.hotelService = hotelService;
    }
    @Override
    public Hotel search() {
        for(Map.Entry<Long, Hotel> hotelEntry:hotelService.hotels.entrySet())
        {

            Hotel curHotel = hotelEntry.getValue();
            if(hotel.getEmail().equals(curHotel.getEmail()))
            {
                if(hotel.getTypesOfRooms().get(room) != 0)
                {
                    avaliable = curHotel;
                    break;
                }
            }
        }
        return avaliable;
    }
}
