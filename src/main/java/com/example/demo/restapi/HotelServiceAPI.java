package com.example.demo.restapi;

import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.service.AddingHotel;
import com.example.demo.service.CheckingHotel;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/travelAgency/hotel")
public class HotelServiceAPI {

   static public HotelService hotelService;

    public HotelServiceAPI(HotelService hotelService) {
        HotelServiceAPI.hotelService = hotelService;
    }

    @PostMapping("/add")
    public Hotel addHotel(@RequestParam String name,@RequestParam String location,@RequestParam String email,@RequestParam Integer numberOfRooms,@RequestParam double pricePerDay,@RequestParam Integer NumberOfSingleRooms,@RequestParam Integer NumberOfDoubleRooms,@RequestParam Integer NumberOfFamilyRooms) {
        Map<Room,Integer> typesOfRooms = new HashMap<>();
        typesOfRooms.put(Room.Single,NumberOfSingleRooms);
        typesOfRooms.put(Room.Double,NumberOfDoubleRooms);
        typesOfRooms.put(Room.Family_Room,NumberOfFamilyRooms);
        Hotel hotel = new Hotel(name,location,email,numberOfRooms,pricePerDay,typesOfRooms);
        AddingHotel addingHotel = new AddingHotel(hotel,hotelService);
        if(addingHotel.add())
        {
            return hotel;
        }
        return null;
    }
    @PostMapping("/exist")
    public String checkingForHotel(@RequestParam String name,@RequestParam String email) {
        Hotel hotel = new Hotel(name,email);
        CheckingHotel checkingHotel = new CheckingHotel(hotel,hotelService);
        String msg = "this hotel is not in system";
        if(checkingHotel.check())
        {
            msg = "this hotel is in system";
        }
        return msg;
    }

    @GetMapping("/AllHotels")
    public Map<Long, Hotel> GetAllHotels() {
        return hotelService.getHotels();
    }


}
