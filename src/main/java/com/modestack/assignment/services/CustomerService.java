package com.modestack.assignment.services;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modestack.assignment.dao.CustomerRepository;
import com.modestack.assignment.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
    private String token = null;
    private String originalValue = null;
	
	public Customer saveCustomer(Customer customer) {
		System.out.println("Saving the Customer details....");
		return customerRepository.save(customer);
		
	}
	
	public boolean customerExists(Customer customer) {
		
		System.out.println("Verifying the Customer exists or not....");
		
		List<Customer> listOfCustomers = customerRepository.findAll();
		
		System.out.println("Customer Name = " + customer.getEmail());
		
		for(Customer user : listOfCustomers) {
			
			System.out.println("User Name = " + user.getEmail());
			
			if ((customer.getEmail().equals(user.getEmail())) || 
					(customer.getUsername().equals(user.getUsername()))) {
				System.out.println("Same Customer...");
				return true;
			}
		}
		System.out.println("Different customer...");
		return false;
	}
	
	public Customer validateCustomer(String username) {
		
		System.out.println("Validating customer...");
		
		return customerRepository.findByUserName(username);
	}
	
    public String encrypt(String originalString) {
        try {
        	
        	token = Base64.getEncoder().encodeToString(originalString.getBytes( "utf-8" ) );
        	System.out.println("Token ====== " + token);
        	
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return token;
    }
 
    public String decrypt(String decryptKey) {
        try {
        	
        	byte[] decodedStr = Base64.getDecoder().decode( decryptKey );
        	System.out.println( "Decoded using basic decoding " + new String( decodedStr, "utf-8" ) );
        	originalValue = new String( decodedStr, "utf-8" );
        	
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.getMessage());
        }
        return originalValue;
    }
	
	
	

}
