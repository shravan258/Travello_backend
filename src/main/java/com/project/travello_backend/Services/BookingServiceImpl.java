package com.project.travello_backend.Services;

import com.project.travello_backend.Dao.BookingDao;
import com.project.travello_backend.Dao.CustomerDao;
import com.project.travello_backend.Dao.HotelDao;
import com.project.travello_backend.Entity.Booking;
import com.project.travello_backend.Entity.Customer;
import com.project.travello_backend.Entity.Hotel;
import com.project.travello_backend.Entity.Room;
import com.project.travello_backend.ExceptionHandlers.BookingsFullException;
import com.project.travello_backend.ExceptionHandlers.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingDao bookingdao;

    @Autowired
    private HotelDao hoteldao;

    @Autowired
    private CustomerDao customerdao;


    @Override
    public Boolean checkRoomAvailability(Integer roomNumber, LocalDate checkoutDate, Integer hotelid) {
        Optional<Hotel> hotel =  hoteldao.findById(hotelid);

        if(hotel.isEmpty()){
            throw new RuntimeException("hotel not found");
        }

        List<Room> hotelrooms = hotel.get().getRooms();

        Room roomdata = null;

        for(Room room:hotelrooms){
            if(room.getRoomNumber() == roomNumber){
                roomdata = room;
            }else{
                throw new RuntimeException("room not found");
            }
        }
        return true;
    }

    @Override
    public Booking addBooking(Integer customerId, Integer HotelId,Booking bookingdata) throws BookingsFullException {

        Optional<Customer> customer = customerdao.findById(customerId);
        if (customer.isEmpty()) {
            throw new RuntimeException("customer not found");
        }
        Optional<Hotel> hotel = hoteldao.findById(HotelId);
        if (hotel.isEmpty()) {
            throw new RuntimeException("hotel not found");
        }
        Booking booking = null;
        if (hotel.get().getTotalRooms() > hotel.get().getOccupiedRooms()) {
            booking = new Booking();
            booking.setCustomer(customer.get());
            booking.setHotel(hotel.get());
            booking.setCheckInDate(bookingdata.getCheckInDate());
            booking.setCheckOutDate(bookingdata.getCheckOutDate());
            booking.setBooking_status(booking.isBooking_status());
            hotel.get().add(booking);
            hotel.get().setOccupiedRooms(hotel.get().getOccupiedRooms()+1);
        }else{
            throw new BookingsFullException("No rooms avaialable at the momnet");
        }
        if(booking!=null){
            bookingdao.save(booking);
        }
        return booking;

    }

    @Override
    public List<Booking> getBookings(Integer id) throws UserNotFoundException {
        Optional<Customer> customer = customerdao.findById(id);
        if(customer.isEmpty()){
            throw new UserNotFoundException("user not found");
        }
        return customer.get().getBookings();
    }

    @Override
    public void cancelBooking(Integer customerid, Integer bookingId) throws Exception {
        Optional<Booking> booking  = bookingdao.findById(bookingId);
        Booking tempBooking = booking.orElseThrow(()->new Exception("booking not found"));
        Hotel hotel = tempBooking.getHotel();
        hotel.setOccupiedRooms(hotel.getOccupiedRooms()-1);
        tempBooking.setHotel(null);
        tempBooking.setCustomer(null);
        bookingdao.save(tempBooking);
        bookingdao.delete(tempBooking);

    }


}
