package com.project.travello_backend.Dao;

import com.project.travello_backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends JpaRepository<Room,Integer> {


}
