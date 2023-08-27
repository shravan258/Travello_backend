package com.project.travello_backend.Services;

import com.project.travello_backend.Entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    public Room editRoom(Integer id, Room roomData);

    public void deleteRoom(Integer id);

    public List<Room> getRooms();

    public Optional<Room> getRoomById(Integer id);

    public List<Room> getEmptyRooms(Integer hotelid);

}
