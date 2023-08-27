package com.project.travello_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private Integer hotelId;

    @Column(name="hotel_name")
    private String hotelName;

    @Column(name="hotel_contact")
    private Integer hotelContact;


    @Column(name="hotel_location")
    private String hotelLocation;

    @Column(name="hotel_image")
    private String hotelImage;


    @Column(name="hotel_address")
    private String hotelAddress;

    @Column(name="hotel_price")
    private Integer hotelPrice;

    @Column(name="hotel_desc")
    private String hotelDesc;

    @Column(name="total_rooms")
    private Integer totalRooms;

    @Column(name="occupied_rooms")
    private Integer occupiedRooms;

    @ElementCollection
    private List<String> ammenities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy="hotel",cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(String hotelName, Integer hotelContact, String hotelLocation, String hotelImage, String hotelAddress, Integer hotelPrice, String hotelDesc, Integer totalRooms, Integer occupiedRooms, List<String> ammenities, List<Room> rooms) {
        this.hotelName = hotelName;
        this.hotelContact = hotelContact;
        this.hotelLocation = hotelLocation;
        this.hotelImage = hotelImage;
        this.hotelAddress = hotelAddress;
        this.hotelPrice = hotelPrice;
        this.hotelDesc = hotelDesc;
        this.totalRooms = totalRooms;
        this.occupiedRooms = occupiedRooms;
        this.ammenities = ammenities;
        this.rooms = rooms;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public Integer getOccupiedRooms() {
        return occupiedRooms;
    }

    public void setOccupiedRooms(Integer occupiedRooms) {
        this.occupiedRooms = occupiedRooms;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer id) {
        this.hotelId = id;
    }

    public String getHotelDesc() {
        return hotelDesc;
    }

    public void setHotelDesc(String hotelDesc) {
        this.hotelDesc = hotelDesc;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public int getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(Integer hotelContact) {
        this.hotelContact = hotelContact;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public List<String> getAmmenities() {
        return ammenities;
    }

    public void setAmmenities(List<String> ammenities) {
        this.ammenities = ammenities;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Integer getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(Integer hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public void addAmenities(String ammenity){
        if (ammenities == null) {
            ammenities = new ArrayList<>();
        }
        ammenities.add(ammenity);

    }

    public void addRooms(Room tempRoom) {


        rooms.add(tempRoom);
        tempRoom.setHotel(this);

    }

    public void add(Booking newBooking){
        bookings.add(newBooking);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelContact=" + hotelContact +
                ", hotelLocation='" + hotelLocation + '\'' +
                ", hotelImage='" + hotelImage + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", hotelPrice=" + hotelPrice +
                ", hotelDesc='" + hotelDesc + '\'' +
                ", totalRooms=" + totalRooms +
                ", occupiedRooms=" + occupiedRooms +
                ", ammenities=" + ammenities +
                ", rooms=" + rooms +

                '}';
    }
}
