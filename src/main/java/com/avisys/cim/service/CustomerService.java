package com.avisys.cim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.avisys.cim.Customer;
import com.avisys.cim.MobileNumber;
import com.avisys.cim.repo.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    
 // Method to find a all customer 
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
 // Method to find a customer by Mobile number
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber);
    }
    
 // Method to find a customer by First Name and Last Name
    public List<Customer> getCustomersByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }
    
 // Method to find a customer by First Name
    public List<Customer> getCustomersByFisrtName(String likeFirstName) {
        if (likeFirstName != null) {
            return customerRepository.findByFirstNameContainingIgnoreCase(likeFirstName);
        } else {
            return customerRepository.findAll();
        }
    }
    
    // Method to find a customer by Last Name
    public List<Customer> getCustomersByLastName(String likeLastName) {
        if (likeLastName != null) {
            return customerRepository.findByLastNameContainingIgnoreCase(likeLastName);
        } else {
            return customerRepository.findAll();
        }
    }
    
   
    // Method to create a new customer
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    
    }
    
    public ResponseEntity<String> createCustomerTwo(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
        if (existingCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create Customer. Mobile number already present.");
        }
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Method to find a customer by mobile number
    public Customer findByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber);
    }

   
   
  
}