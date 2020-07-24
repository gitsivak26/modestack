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

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(path = "/register")
	public ResponseEntity<?> customerRegistration(@ModelAttribute Customer customer) {
		System.out.println("Registration API called...");
		
		boolean isCustomerExists = customerService.customerExists(customer);
		System.out.println(isCustomerExists);
		
		if (!isCustomerExists) {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate datas not allowed.");
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> customerLogin(@RequestParam String username, 
			@RequestParam String password) {
		
		System.out.println("Login API called...");
		System.out.println("username = " + username + "and password = " + password);
		
		Customer customerFromDb = customerService.validateCustomer(username);
		
		if (password.equals(customerFromDb.getPassword())) {
			return ResponseEntity.status(HttpStatus.OK).body(customerFromDb);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Un Authorized.");
	}
}
