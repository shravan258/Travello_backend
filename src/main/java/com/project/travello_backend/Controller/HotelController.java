package com.project.travello_backend.Controller;


import com.project.travello_backend.Entity.Hotel;
import com.project.travello_backend.Entity.Room;
import com.project.travello_backend.ExceptionHandlers.RoomExistsException;
import com.project.travello_backend.RequestEntity.FilterRangeResponse;
import com.project.travello_backend.RequestEntity.HotelResponse;
import com.project.travello_backend.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelservice;
    @GetMapping("/getallhotels")
    public List<HotelResponse> getallhotels(){
        List<Hotel> hotels = hotelservice.getallHotels();
        return hotels.stream().map(this::hotelToHotelResponse).toList();
    }

    public HotelResponse hotelToHotelResponse(Hotel hotel){
        HotelResponse response = new HotelResponse();
        response.setId(hotel.getHotelId());
        response.setHotelName(hotel.getHotelName());
        response.setHotelLocation(hotel.getHotelLocation());
        response.setHotelPrice(hotel.getHotelPrice());
        response.setTotalRooms(hotel.getTotalRooms());
        response.setOccupiedRooms((hotel.getOccupiedRooms()));
        return response;
    }
    @GetMapping("/{location}")
    public List<Hotel> findhotelbylocation(@PathVariable String location){
        List<Hotel> hotels = hotelservice.findHotelByLocation(location);
        return hotels;
    }

    @PostMapping("/addhotel")
    public Hotel addHotel(@RequestBody Hotel newhotel){
        Hotel newHotel = hotelservice.addHotel(newhotel);
        return newHotel;
    }

    @PostMapping("/addroom/{hotelid}")
    public String addroomstoHotel(@RequestBody Room newroom, @PathVariable Integer hotelid) throws RoomExistsException {
        String message = hotelservice.addRoomstoHotel(hotelid, newroom);
        return message;
    }

    @GetMapping("/allhotelslocation")
    public List<String> getallhotelslocation(){
        return hotelservice.getAllLocations();
    }

    @PostMapping("/filterhotel")
    public List<Hotel> filterbypriceandammenity(@RequestParam(name = "location") String location, @RequestParam(name="Maxprice",required = false) Integer Maxprice,@RequestParam(name="Minprice",required = false) Integer Minprice,@RequestBody(required = false) List<String> ammenity){
          return hotelservice.filterByPriceAndAmmentites(location,Maxprice,ammenity,Minprice);

    }

    @GetMapping("/findbyid/{hotelid}")
    public Hotel getHotelById(@PathVariable Integer hotelid){
        Hotel hotel =  hotelservice.getHotelById(hotelid);
        return hotel;
    }

    @GetMapping("/searchhotel")
    public List<Hotel> searchHotel(@RequestParam(name="location",required = false) String location, @RequestParam(name="hotelname",required = false) String hotelname){
        return hotelservice.findHotelByLocationAndName(location, hotelname);
    }

    @GetMapping("/getammenities/{id}")
    public List<String> getammenitiesByHotelId(@PathVariable Integer id){
        List<String> ammenities = hotelservice.getHotelAmmenities(id);
        return ammenities;
    }

    @PutMapping("/edithotel/{id}")
    public Hotel editsavedHotel(@RequestBody Hotel hotel,@PathVariable Integer id){
        Hotel editedHotel = hotelservice.editHotel(hotel, id);
        return editedHotel;
    }

    @DeleteMapping("/delete/{id}")
    public void deletesavedHotel(@PathVariable Integer id){
        hotelservice.deleteHotel(id);
    }

    @GetMapping("/getfiltervalues/{location}")
    public FilterRangeResponse getfilterpricevalues(@PathVariable String location){
        System.out.println(location);
        return hotelservice.filterHotelPriceByLocation(location);
    }






}
