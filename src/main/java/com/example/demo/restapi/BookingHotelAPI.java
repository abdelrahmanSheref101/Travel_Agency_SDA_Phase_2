package com.example.demo.restapi;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travelAgency/BookingHotel")
public class BookingHotelAPI
{

    @Autowired
    BookingHotel bookingHotel;


    public BookingHotelAPI(BookingHotel bookingHotel)
    {
        this.bookingHotel = bookingHotel;



    }



    @PostMapping("/BookRoom")
    public String bookingHotel(String hotelName, String emailUser, Room room, LocalDate date)  {
        SearchingHotelByName searchingHotelByName = new SearchingHotelByName(hotelName, bookingHotel.hotelService);
        SearchForUser searchForUser = new SearchForUser(bookingHotel.userService,emailUser);
        Hotel hotel = searchingHotelByName.search();
        User user = searchForUser.search();
        if(user != null && hotel != null)
        {
            bookingHotel.hotel = hotel;
            bookingHotel.user = user;
            bookingHotel.date = date;
            bookingHotel.room = room;



            return bookingHotel.book();
        }

        return null;





    }
    @GetMapping("/AllBooking")
    public Map<Hotel, List<User>> getAllBooking()
    {

        return bookingHotel.getDataOfBooking();
    }







}
