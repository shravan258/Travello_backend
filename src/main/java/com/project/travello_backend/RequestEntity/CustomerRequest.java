package com.project.travello_backend.RequestEntity;

import com.project.travello_backend.Entity.Booking;

import java.util.List;

public class CustomerRequest {

    private String customerName;
    private String customerEmail;
    private Long customerPhone;
    private String customerLocation;
    private String customerPassword;
    private List<Booking> bookings;


}
