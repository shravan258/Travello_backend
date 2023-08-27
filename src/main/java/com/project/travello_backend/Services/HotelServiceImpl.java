package com.project.travello_backend.Services;

import com.project.travello_backend.Dao.HotelDao;
import com.project.travello_backend.Entity.Hotel;
import com.project.travello_backend.Entity.Room;
import com.project.travello_backend.ExceptionHandlers.RoomExistsException;
import com.project.travello_backend.RequestEntity.FilterRangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hoteldao;





    @Override
    public List<Hotel> getallHotels() {
        List<Hotel> hotels = hoteldao.findAll();
        return hotels;
    }

    @Override
    public List<Hotel> findHotelByLocation(String location) {

        List<Hotel> list = hoteldao.findByHotelLocation(location);
        System.out.println(list);
        return hoteldao.findByHotelLocation(location);
    }

    @Override
    public FilterRangeResponse filterHotelPriceByLocation(String location) {
        Integer Max_price = hoteldao.findMaxpriceAtLocation(location);
        Integer Min_price = hoteldao.findMinpriceAtLocation(location);
        FilterRangeResponse filter_values= new FilterRangeResponse();
        filter_values.setMaxPrice(Max_price);
        filter_values.setMinPrice(Min_price);
        return filter_values;
    }

    @Override
    public Hotel addHotel(Hotel newhotel) {

        return hoteldao.save(newhotel);
    }

    @Override
    public String addRoomstoHotel(Integer hotelId , Room newroom) throws RoomExistsException {
        Optional<Hotel> hotel = hoteldao.findById(hotelId);

        if(hotel.isEmpty()){
            throw new RuntimeException("hotel with this id not found");
        }

        List<Room> hotelrooms = hotel.get().getRooms();

        Boolean canCreateRoom = true;

        for(Room room : hotelrooms ){
            if(room.getRoomNumber() == newroom.getRoomNumber()){
                canCreateRoom = false;
            }
        }
        if(canCreateRoom){
            Hotel tempHotel = hotel.get();
            tempHotel.addRooms(newroom);
            newroom.setHotel(tempHotel);
            Hotel savedHotel = hoteldao.save(tempHotel);
            return "room saved";
        }else{
            throw new RoomExistsException("room exists");

        }

    }

    @Override
    public List<String> getAllLocations() {
        List<String> locations = hoteldao.getAllLocations();
        return locations;
    }

    @Override
    public List<Hotel> filterByPriceAndAmmentites(String location, Integer Maxprice, List<String> amenities,Integer Minprice) {


        List<Hotel> hotels = hoteldao.findByHotelLocation(location);
        System.out.println(hotels);
        List<Hotel> filteredHotel = null;
        List<Hotel> tempHotel = null;
        if(amenities!=null && !amenities.isEmpty()){
            System.out.println("3");
            System.out.println(hotels);
            for(int i=0;i<amenities.size();i++){
                int finalI = i;
                System.out.println(i);
                filteredHotel =  hotels.stream().filter(hotel->hotel.getAmmenities().contains(amenities.get(finalI))).toList();
//                System.out.println(filteredHotel.get(1).getAmmenities());

            }
            System.out.println("out of loop");
        }
        if(Maxprice !=0 && Minprice!=0){
            System.out.println("hii");
            if(filteredHotel != null){
                filteredHotel = filteredHotel.stream().filter(hotel->hotel.getHotelPrice()>= Minprice &&hotel.getHotelPrice() <= Maxprice).toList();
                System.out.println("inside price filter");
            }else{

                System.out.println("hello");
                hotels = hotels.stream().filter(hotel->hotel.getHotelPrice() >= Minprice &&hotel.getHotelPrice() <= Maxprice).toList();

            }

        }
        if(filteredHotel == null){
            return hotels;
        }else{
            return filteredHotel;
        }
    }

    @Override
    public Hotel getHotelById(Integer id) {
        Optional<Hotel> hotel = hoteldao.findById(id);

        if(hotel.isEmpty()){
            throw new RuntimeException("no hotel found with this id ");
        }

        return hotel.get();
    }

    @Override
    public Hotel editHotel(Hotel hotel, Integer id) {
        Optional<Hotel> temphotel = hoteldao.findById(id);

        if(temphotel.isEmpty()){
            throw new RuntimeException("no hotel found with this id ");
        }
        System.out.println(hotel.getAmmenities());
        temphotel.get().setHotelName(hotel.getHotelName());
        temphotel.get().setHotelAddress(hotel.getHotelAddress());
        temphotel.get().setHotelDesc(hotel.getHotelDesc());
        temphotel.get().setHotelContact(hotel.getHotelContact());
        temphotel.get().setHotelImage(hotel.getHotelImage());
        temphotel.get().setHotelPrice(hotel.getHotelPrice());
        List<String> tempAmmneities = hotel.getAmmenities();
        temphotel.get().setAmmenities(tempAmmneities);

        return hoteldao.save(temphotel.get());
    }



    @Override
    public List<String> getHotelAmmenities(Integer id) {
        Optional<Hotel> hotel = hoteldao.findById(id);
        if(hotel == null){
            throw new RuntimeException("hotel not found");
        }
        List<String> ammenities = hotel.get().getAmmenities();
        return ammenities;
    }

    @Override
    public void deleteHotel(Integer id) {
        Optional<Hotel> hotel = hoteldao.findById(id);
        if(hotel == null){
            throw new RuntimeException("hotel not found");
        }
        hotel.get().setAmmenities(null);
        hotel.get().setRooms(null);
        hoteldao.delete(hotel.get());
    }

    @Override
    public List<Hotel> findHotelByLocationAndName(String location, String name) {
        List<Hotel> hotels = hoteldao.searchByLocationAndName(location, name);
        return hotels;
    }


}
