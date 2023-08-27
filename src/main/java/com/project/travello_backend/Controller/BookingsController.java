package com.project.travello_backend.Controller;


import com.project.travello_backend.Entity.Booking;
import com.project.travello_backend.ExceptionHandlers.BookingsFullException;
import com.project.travello_backend.ExceptionHandlers.UserNotFoundException;
import com.project.travello_backend.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingsController {

    @Autowired
    private BookingService bookingservice ;

//    @PostMapping("/checkroomavailability")
//    public Boolean checkroomavailability(@RequestBody RoomAvialabilityRequest roomdata){
//
//    }

    @PostMapping("/addbooking/{hotelid}/{customerid}")
    public Booking addnewbooking(@RequestBody Booking bookingdata, @PathVariable Integer hotelid,@PathVariable Integer customerid) throws BookingsFullException {
        return bookingservice.addBooking(customerid, hotelid, bookingdata);
    }

    @GetMapping("/getbookings/{id}")
    public List<Booking> getBookingsCustomer(@PathVariable Integer id) throws UserNotFoundException {
        return bookingservice.getBookings(id);
    }

    @DeleteMapping("/deletebooking/{customerid}/{bookingid}")
    public void deleteBooking(@PathVariable Integer customerid,@PathVariable Integer bookingid) throws Exception {
        bookingservice.cancelBooking(customerid, bookingid);
    }
}
