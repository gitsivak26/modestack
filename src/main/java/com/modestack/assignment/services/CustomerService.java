package com.modestack.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modestack.assignment.dao.CustomerRepository;
import com.modestack.assignment.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer) {
		
		return customerRepository.save(customer);

	}
	
	public boolean customerExists(Customer customer) {

		List<Customer> listOfCustomers = customerRepository.findAll();
		
		for(Customer user : listOfCustomers) {

			if ((customer.getEmail().equals(user.getEmail())) || 
					(customer.getUsername().equals(user.getUsername()))) return true;
		}
		
		return false;
	}
	
	public Boolean validCustomer(String username, String password) {
		
		Customer customerFromDb = getCustomerDetails(username);
		
		if (password.equals(customerFromDb.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	public Customer getCustomerDetails(String username) {

		return customerRepository.findByUserName(username);
	}
}
