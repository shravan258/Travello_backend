package com.project.travello_backend.Dao;

import com.project.travello_backend.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDao extends JpaRepository<Location,Integer> {
}
