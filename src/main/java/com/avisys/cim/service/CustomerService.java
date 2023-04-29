package com.avisys.cim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.avisys.cim.Customer;
import com.avisys.cim.repo.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Customer getCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber);
    }
    
    public List<Customer> getCustomersByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }
    
    public List<Customer> getCustomersByFisrtName(String likeFirstName) {
        if (likeFirstName != null) {
            return customerRepository.findByFirstNameContainingIgnoreCase(likeFirstName);
        } else {
            return customerRepository.findAll();
        }
    }
    
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

    // Method to find a customer by mobile number
    public Customer findByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber);
    }

   
  
}