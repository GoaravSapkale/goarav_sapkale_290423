package com.avisys.cim.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avisys.cim.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	// Method to find all customer
	List<Customer> findAll();
	// Method to find by mobile Number
	Customer findByMobileNumber(String mobileNumber);
	
	// Method to find customer firstname and last name
	 List<Customer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
	// Method to find by first name
	 List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
	 
	// Method to find by last Name
	 List<Customer> findByLastNameContainingIgnoreCase(String lastName);
	 
	 Optional<Customer> findByMobileNumbers(String mobileNumber);
	
}
