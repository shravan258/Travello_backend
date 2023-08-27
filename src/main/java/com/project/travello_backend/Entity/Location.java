package com.project.travello_backend.Entity;


import jakarta.persistence.*;

@Entity
@Table(name="locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location_name;

    private String location_desc;

    private String location_image;

    private String location_country;

    public Location() {
    }

    public Location(String location_name, String location_desc, String location_image,String location_country) {
        this.location_name = location_name;
        this.location_desc = location_desc;
        this.location_image = location_image;
        this.location_country = location_country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_desc() {
        return location_desc;
    }

    public void setLocation_desc(String location_desc) {
        this.location_desc = location_desc;
    }

    public String getLocation_image() {
        return location_image;
    }

    public void setLocation_image(String location_image) {
        this.location_image = location_image;
    }

    public String getLocation_country() {
        return location_country;
    }

    public void setLocation_country(String location_country) {
        this.location_country = location_country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", location_name='" + location_name + '\'' +
                ", location_desc='" + location_desc + '\'' +
                ", location_image='" + location_image + '\'' +
                ", location_country='" + location_country + '\'' +
                '}';
    }
}
