package com.project.travello_backend.Dao;

import com.project.travello_backend.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelDao extends JpaRepository<Hotel,Integer> {

    public List<Hotel> findByHotelLocation(String location);

    @Query("SELECT DISTINCT hotelLocation FROM Hotel")
    public List<String> getAllLocations();

    @Query("SELECT MAX(h.hotelPrice) AS highest_price FROM Hotel as h WHERE h.hotelLocation = ?1")
    public Integer findMaxpriceAtLocation(String location);


    @Query("SELECT h FROM Hotel h WHERE h.hotelPrice = (SELECT MIN(h2.hotelPrice) FROM Hotel h2 WHERE h2.hotelLocation = ?1)")
    Hotel findHotelWithLowestPriceByLocation(String location);



    @Query("SELECT MIN(h.hotelPrice) AS highest_price FROM Hotel as h WHERE h.hotelLocation = ?1")
    public Integer findMinpriceAtLocation(String location);

    @Query("SELECT h FROM Hotel as h WHERE h.hotelLocation like %?1% OR h.hotelName like %?2%")
    public List<Hotel> searchByLocationAndName(String location,String name);








}
