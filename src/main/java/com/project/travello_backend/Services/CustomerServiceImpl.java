package com.project.travello_backend.Services;


import com.project.travello_backend.Dao.CustomerDao;
import com.project.travello_backend.Entity.Booking;
import com.project.travello_backend.Entity.Customer;
import com.project.travello_backend.Entity.Favorite;
import com.project.travello_backend.ExceptionHandlers.UserAlreadyExistException;
import com.project.travello_backend.ExceptionHandlers.UserNotFoundException;
import com.project.travello_backend.RequestEntity.AuthenticationRequest;
import com.project.travello_backend.RequestEntity.AuthenticationResponse;
import com.project.travello_backend.security.JwtService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.travello_backend.Entity.Role.USER;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerdao;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Customer addCustomer(Customer newCustomer) throws UserAlreadyExistException {
        String email = newCustomer.getCustomerEmail();
        Optional<Customer> checkCustomer = customerdao.findbyCustomerEmail(email);

        if(checkCustomer.isPresent()){
            throw new UserAlreadyExistException("customer already exists with this email");
        }
        for (Booking booking : newCustomer.getBookings()) {
            booking.setCustomer(newCustomer);
        }
        newCustomer.setCustomerPassword(passwordEncoder.encode(newCustomer.getCustomerPassword()));
        if(newCustomer.getRole() == null){
            newCustomer.setRole(USER);
        }
        Customer savedCustomer = customerdao.save(newCustomer);
        return savedCustomer;

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {

        Optional<Customer> user = customerdao.findbyCustomerEmail(request.getEmail());
        if(user.isEmpty()){
            throw new UserNotFoundException("no user exist");
        }

        boolean matches = passwordEncoder.matches(request.getPassword(),user.get().getCustomerPassword() );
        if(!matches){
            throw new BadCredentialsException("wrong password");
        }

        var jwtToken = jwtService.generateToken((UserDetails) user.get());
        return new AuthenticationResponse(jwtToken,user.get().getId(),user.get().getRole());
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerdao.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerdao.findAll();
    }

    @Override
    public void deleteCustomer(Integer id) throws UserNotFoundException {
        Optional<Customer> customer = customerdao.findById(id);
        if(customer.isEmpty()){
            throw new UserNotFoundException("user not found");
        }
        customer.get().setBookings(null);
        customer.get().setFavorites(null);
        customerdao.delete(customer.get());
    }


    @Override
    public Customer editCustomer(Customer customer, Integer id) {
        Optional<Customer> savedcustomer = getCustomerById(id);
        if(savedcustomer == null){
            throw new RuntimeException("customer not found");
        }
        savedcustomer.get().setCustomerName(customer.getCustomerName()); //email phone location role fav bookings
        savedcustomer.get().setCustomerEmail(customer.getCustomerEmail());
        savedcustomer.get().setCustomerPhone(customer.getCustomerPhone());
        savedcustomer.get().setCustomerLocation(customer.getCustomerLocation());
        savedcustomer.get().setRole(customer.getRole());
        List<Favorite> tempFav = customer.getFavorites();
        savedcustomer.get().setFavorites(tempFav);
        List<Booking> tempBookings = customer.getBookings();
        savedcustomer.get().setBookings(tempBookings);
        return customerdao.save(savedcustomer.get());


    }

    @Override
    public List<Customer> searchCustomer(String paramstr) {
//        Role roletemp = Role.valueOf(role);
        String name = null;
        String email = null;
        String location = null;
        String role = null;
        Long phone = null;
        String[] inputStr = paramstr.split("_");
        for (String pair : inputStr) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                switch (key) {
                    case "name":
                        name = value;
                        break;
                    case "email":
                        email = value;
                        break;
                    case "location":
                        location = value;
                        break;
                    case "phone":
                        phone = (long) Integer.parseInt(value);
                    case "role":
                        role = value;
                        break;
                    default:
                        // Handle unknown keys (if needed)
                        break;
                }
            }
        }

        if (name == null && email == null && phone == null && location == null && role == null) {
            return customerdao.findAll();
        } else {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> itemRoot = criteriaQuery.from(Customer.class);

            List<Predicate> predicates = new ArrayList<>();


            if (name != null && (!name.trim().isEmpty())) {
                predicates.add(criteriaBuilder.like(itemRoot.get("customerName"), "%" + name + "%"));
            }
            if (location != null && (!location.trim().isEmpty())) {
                System.out.println("hii");
                predicates.add(criteriaBuilder.like(itemRoot.get("customerLocation"), "%" + location + "%"));
            }
            if (role != null && (!role.trim().isEmpty())) {
                predicates.add(criteriaBuilder.like(itemRoot.get("role").as(String.class), "%" + role + "%"));
            }
            if (email != null && (!email.trim().isEmpty())) {
                predicates.add(criteriaBuilder.like(itemRoot.get("customerEmail"), "%" + email + "%"));
            }
            if (phone != null) {
                predicates.add(criteriaBuilder.equal(itemRoot.get("customerPhone"), phone));
            }


            if (!predicates.isEmpty()) {
                Predicate andPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                criteriaQuery.where(andPredicate);
            }
            List<Customer> items = entityManager.createQuery(criteriaQuery).getResultList();
            return items;
        }


    }}



