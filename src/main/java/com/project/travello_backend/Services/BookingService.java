package com.project.travello_backend.Services;

import com.project.travello_backend.Entity.Booking;
import com.project.travello_backend.ExceptionHandlers.BookingsFullException;
import com.project.travello_backend.ExceptionHandlers.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    public Boolean checkRoomAvailability(Integer roomNumber, LocalDate checkoutDate, Integer hotelid);
    public Booking addBooking(Integer customerId,Integer HotelId,Booking bookingdata) throws BookingsFullException;
    public List<Booking> getBookings(Integer id) throws UserNotFoundException;

    public void cancelBooking(Integer customerid,Integer bookingId) throws Exception;



}
