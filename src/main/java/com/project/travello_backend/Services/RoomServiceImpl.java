package com.project.travello_backend.Services;

import com.project.travello_backend.Dao.HotelDao;
import com.project.travello_backend.Dao.RoomDao;
import com.project.travello_backend.Entity.Hotel;
import com.project.travello_backend.Entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    private HotelDao hoteldao;
    @Autowired
    private RoomDao roomdao;
    @Override
    public Room editRoom(Integer id, Room roomData) {
        Optional<Room> temproom = roomdao.findById(id);

        if(temproom.isEmpty()){
            throw new RuntimeException("no hotel found with this id ");
        }
        temproom.get().setRoomNumber(roomData.getRoomNumber());
        temproom.get().setRoomAvailability(roomData.isRoomAvailability());
        return roomdao.save(temproom.get());
    }

    @Override
    public void deleteRoom(Integer id) {
        Optional<Room> room = roomdao.findById(id);
        if(room == null){
            throw new RuntimeException("hotel not found");
        }
        room.get().setHotel(null);
        roomdao.delete(room.get());
    }


    @Override
    public List<Room> getRooms() {
        return roomdao.findAll();
    }

    @Override
    public Optional<Room> getRoomById(Integer id) {
        return roomdao.findById(id);
    }

    @Override
    public List<Room> getEmptyRooms(Integer hotelid) {
       Optional<Hotel> hotel = hoteldao.findById(hotelid);
       if(hotel.isEmpty()){
           throw new RuntimeException("hotel not found");
       }

       List<Room> rooms = hotel.get().getRooms();

       List<Room>filteredrooms = rooms.stream().filter(room-> room.isRoomAvailability()).toList();
       return filteredrooms;
    }


}
