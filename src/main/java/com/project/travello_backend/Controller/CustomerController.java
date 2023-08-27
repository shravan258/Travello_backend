package com.project.travello_backend.Controller;


import com.project.travello_backend.Entity.Customer;
import com.project.travello_backend.ExceptionHandlers.UserAlreadyExistException;
import com.project.travello_backend.ExceptionHandlers.UserNotFoundException;
import com.project.travello_backend.RequestEntity.AuthenticationRequest;
import com.project.travello_backend.RequestEntity.AuthenticationResponse;
import com.project.travello_backend.RequestEntity.CustomerResponse;
import com.project.travello_backend.Services.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @PostMapping("/addcustomer")
    public Customer addcustomer(@RequestBody Customer customer) throws UserAlreadyExistException {
        Customer newcustomer = customerservice.addCustomer(customer);
        return newcustomer;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(customerservice.authenticate(request));
    }

    @GetMapping("/{id}")
    public Customer getcustomerbyid(@PathVariable Integer id){

        return customerservice.getCustomerById(id).get();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/getallcustomers")
    public List<CustomerResponse> getcustomers(){
        return customerservice.getAllCustomers().stream().map(this::customerToCustomerResponse).toList();
    }

    public CustomerResponse customerToCustomerResponse(Customer customer){
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setCustomerName(customer.getCustomerName());
        response.setCustomerEmail(customer.getCustomerEmail());
        response.setCustomerLocation(customer.getCustomerLocation());
        response.setCustomerPhone(customer.getCustomerPhone());
        response.setRole(customer.getRole());
        return response;
    }
    @PutMapping("/editcustomer/{id}")
    public Customer editsavedCustomer(@RequestBody Customer customer, @PathVariable Integer id){
        Customer editedCustomer = customerservice.editCustomer(customer, id);
        return editedCustomer;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Integer id) throws UserNotFoundException {
        customerservice.deleteCustomer(id);
    }

    @GetMapping("/searchcustomer")
    public List<CustomerResponse> searchcustomer(@RequestParam(name="paramstr",required = false) String paramstr){
        List<Customer> items = customerservice.searchCustomer(paramstr);
        return items.stream().map(this::customerToCustomerResponse).toList();
    }


}
