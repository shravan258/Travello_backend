package com.project.travello_backend.Services;

import com.project.travello_backend.Dao.LocationDao;
import com.project.travello_backend.Entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationDao locationdao;


    @Override
    public List<Location> getAllLocations() {
        return locationdao.findAll();
    }

    @Override
    public Location AddLocation(Location newLocation) {
        return locationdao.save(newLocation);
    }
}
