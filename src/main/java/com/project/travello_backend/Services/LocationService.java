package com.project.travello_backend.Services;

import com.project.travello_backend.Entity.Location;

import java.util.List;

public interface LocationService {
    public List<Location> getAllLocations();

    public Location AddLocation(Location newLocation);
}
