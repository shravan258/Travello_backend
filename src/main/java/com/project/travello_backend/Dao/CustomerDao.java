package com.project.travello_backend.Dao;

import com.project.travello_backend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {
        @Query("SELECT u FROM Customer u WHERE u.customerEmail = ?1")
        public Optional<Customer> findbyCustomerEmail(String email);
        }



