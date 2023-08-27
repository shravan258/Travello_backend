package com.project.travello_backend.Services;

import com.project.travello_backend.Entity.Hotel;
import com.project.travello_backend.Entity.Room;
import com.project.travello_backend.ExceptionHandlers.RoomExistsException;
import com.project.travello_backend.RequestEntity.FilterRangeResponse;

import java.util.List;

public interface HotelService {
    public List<Hotel> getallHotels();

    public List<Hotel> findHotelByLocation(String location);

    public FilterRangeResponse filterHotelPriceByLocation(String location);

    public Hotel addHotel(Hotel newhotel);

    public String addRoomstoHotel(Integer hotelid,Room newroom) throws RoomExistsException;

    public List<String> getAllLocations();

    public List<Hotel> filterByPriceAndAmmentites(String location, Integer Maxprice, List<String> ammenity,Integer Minprice);

    public Hotel getHotelById(Integer id );

    public Hotel editHotel(Hotel hotel, Integer id);

    public List<String> getHotelAmmenities(Integer id);

    public void deleteHotel(Integer id);

    public List<Hotel> findHotelByLocationAndName(String location,String name);



}
