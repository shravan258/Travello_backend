package com.project.travello_backend.Controller;

import com.project.travello_backend.Entity.Room;
import com.project.travello_backend.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {


    @Autowired
    private RoomService roomservice;

    @GetMapping("getallrooms")
    public List<Room> getrooms(){
        return roomservice.getRooms();
    }

    @PutMapping("editroom/{id}")
    public Room editsavedRoom(@RequestBody Room roomdata, @PathVariable Integer id){
            return roomservice.editRoom(id,roomdata);
    }

    @DeleteMapping("delete/{id}")
    public void deleteRoom(@PathVariable Integer id){
        roomservice.deleteRoom(id);
    }

    @GetMapping("getroombyid/{id}")
    public Room getRoomByid(@PathVariable Integer id){
        Optional<Room> room =  roomservice.getRoomById(id);
        return room.get();
    }

    @GetMapping("/getemptyrooms/{id}")
    public List<Room> getemptyRooms(@PathVariable Integer id){
        return roomservice.getEmptyRooms(id);
    }
}
