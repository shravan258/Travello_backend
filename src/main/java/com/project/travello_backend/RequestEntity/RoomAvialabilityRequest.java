package com.project.travello_backend.RequestEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class RoomAvialabilityRequest {
    private Integer roomNumber;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkoutDate;

    private Integer hotelid;

    public RoomAvialabilityRequest() {
    }

    public RoomAvialabilityRequest(Integer roomNumber, LocalDate checkoutDate, Integer hotelid) {
        this.roomNumber = roomNumber;
        this.checkoutDate = checkoutDate;
        this.hotelid = hotelid;
    }

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "RoomAvialabilityRequest{" +
                "roomNumber=" + roomNumber +
                ", checkoutDate=" + checkoutDate +
                ", hotelid=" + hotelid +
                '}';
    }
}
