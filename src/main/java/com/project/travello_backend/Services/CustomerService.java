package com.project.travello_backend.Services;

import com.project.travello_backend.Entity.Customer;
import com.project.travello_backend.ExceptionHandlers.UserAlreadyExistException;
import com.project.travello_backend.ExceptionHandlers.UserNotFoundException;
import com.project.travello_backend.RequestEntity.AuthenticationRequest;
import com.project.travello_backend.RequestEntity.AuthenticationResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Customer addCustomer(Customer newCustomer) throws UserAlreadyExistException;

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException;

    public Optional<Customer> getCustomerById(Integer id);
    public List<Customer> getAllCustomers();
    public void deleteCustomer(Integer id) throws UserNotFoundException;
    public Customer editCustomer(Customer customer, Integer id);

    public List<Customer> searchCustomer(String paramstr);





}
