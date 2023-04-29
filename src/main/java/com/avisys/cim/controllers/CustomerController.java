package com.avisys.cim.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.dto.CustomerDTO;
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
    
    
  //methode for creating new customer
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            // Check if mobile number already exists in database
            if (customerService.findByMobileNumber(customer.getMobileNumber()) != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create Customer. Mobile number already present.");
            }
            // Create new customer
            customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to create Customer. " + e.getMessage());
        }
    }
    
  //methode for creating new customer after updating
    @PostMapping("/updated")
    public ResponseEntity<String> createCustomerTwo(@RequestBody CustomerDTO customer) {
	Customer customer1 = new Customer();
    customer1.setFirstName(customer.getFirstName());
    customer1.setLastName(customer.getLastName());
    for (String number : customer.getMobileNumbers()) {
        customer1.addMobileNumber(number);
    }
    
	return customerService.createCustomerTwo(customer1);
        
    }
    
    
    //methode for delete customer by mobile number
    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<String> deleteCustomerByMobileNumber(@PathVariable String mobileNumber) {
        boolean deleted = customerService.deleteCustomerByMobileNumber(mobileNumber);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with mobile number: " + mobileNumber);
        }
    }
    
    @PutMapping("/{mobileNumber}")
    public ResponseEntity<String> updateMobileNumber(@PathVariable String mobileNumber, @RequestBody String newMobileNumber) {
        boolean result = customerService.updateMobileNumber(mobileNumber, newMobileNumber);
        if (result) {
            return ResponseEntity.ok("Mobile number updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
   
}
