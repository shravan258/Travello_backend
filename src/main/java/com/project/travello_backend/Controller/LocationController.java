package com.project.travello_backend.Controller;


import com.project.travello_backend.Entity.Location;
import com.project.travello_backend.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationservice;

    @PostMapping("/addlocation")
    public Location addLocation(@RequestBody Location newLocation){
        return locationservice.AddLocation(newLocation);
    }

    @GetMapping("/alllocations")
    public List<Location> alllocations(){
        return locationservice.getAllLocations();
    }
}
