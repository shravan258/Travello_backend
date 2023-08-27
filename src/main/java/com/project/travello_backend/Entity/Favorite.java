package com.project.travello_backend.Entity;

import jakarta.persistence.*;

@Table(name="favorites")
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
