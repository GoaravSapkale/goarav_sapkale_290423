package com.avisys.cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}