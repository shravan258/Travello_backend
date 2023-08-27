package com.project.travello_backend.Dao;

import com.project.travello_backend.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking,Integer> {
}
