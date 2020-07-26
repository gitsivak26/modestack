package com.modestack.assignment.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.assignment.model.Customer;
import com.modestack.assignment.services.CustomerService;
import com.modestack.assignment.services.SecurityService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	SecurityService securityService;
	
	@PostMapping(path = "/register")
	public ResponseEntity<?> customerRegistration(@ModelAttribute Customer customer) {
		
		boolean isCustomerExists = customerService.customerExists(customer);
		
		if (!isCustomerExists) {
			
			Customer customerFromDB = customerService.saveCustomer(customer);
			
			String accessToken = securityService.encrypt(customer.getUsername());
			
			customerFromDB.setAccessToken(accessToken);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(customerFromDB);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate datas not allowed.");
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> customerLogin(@RequestParam String username, 
			@RequestParam String password) {

		Boolean isValidCustomer = customerService.validCustomer(username, password);
		
		if (isValidCustomer) {
			
			Customer customerFromDb = customerService.getCustomerDetails(username);
			
			customerFromDb.setAccessToken(securityService.encrypt(username));
			
			return ResponseEntity.status(HttpStatus.OK).body(customerFromDb);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not a valid user.");
	}
}
