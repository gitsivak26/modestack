package com.modestack.assignment.services;

import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	
    private String accessToken = null;
    private String originalValue = null;
    
	public String encrypt(String originalString) {
        try {
        	
        	accessToken = Base64.getEncoder().encodeToString(originalString.getBytes("utf-8" ));
        	
        } catch (Exception e) {
            System.out.println("Error while encrypting... " + e.getStackTrace());
        }
        
        return accessToken;
    }
 
    public String decrypt(String decryptKey) {
        try {

        	byte[] decodedStr = Base64.getDecoder().decode(decryptKey);
        	
        	originalValue = new String(decodedStr, "utf-8");
        	
        } catch (Exception e) {
            System.out.println("Error while decrypting... " + e.getStackTrace());
        }
        
        return originalValue;
    }
}
