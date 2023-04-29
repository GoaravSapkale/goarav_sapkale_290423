package com.avisys.cim.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    //Request for get all customer  url:/customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
  //Request for get customer by Mobile number  url:/customers?mobileNumber=9111111111
    @GetMapping(params = {"mobileNumber"})
    public ResponseEntity<Customer> getCustomerByMobileNumber(@RequestParam("mobileNumber") String mobileNumber) {
        Customer customer = customerService.getCustomerByMobileNumber(mobileNumber);
        return ResponseEntity.ok(customer);
    }
    
  //Request for get customer by FirstName   url:/customers/firstName?likeFirstName=Kathy
    @GetMapping("/firstName")
    public List<Customer> getCustomersByFisrtName(@RequestParam(required = false) String likeFirstName) {
        return customerService.getCustomersByFisrtName(likeFirstName);
    }
    
  //Request for get customer by LastName=Sierra
    @GetMapping("/lastName")
    public List<Customer> getCustomersByLastName(@RequestParam(required = false) String likeLastName) {
        return customerService.getCustomersByLastName(likeLastName);
    }
    
    
  //Request for get customer by FirstName&LastName Url: /customers?firstName=Kat&lastName=Si
    @GetMapping(params = {"firstName", "lastName"})
    public ResponseEntity<List<Customer>> getCustomersByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        List<Customer> customers = customerService.getCustomersByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(customers);
    }
    
}
