package com.avisys.cim.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avisys.cim.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findAll();
	Customer findByMobileNumber(String mobileNumber);
	 List<Customer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
	 List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
	 List<Customer> findByLastNameContainingIgnoreCase(String lastName);
}
