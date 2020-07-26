package com.modestack.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modestack.assignment.model.Customer;
import com.modestack.assignment.services.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Customer customer;

    @MockBean
    private CustomerService customerService;
    
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setUsername("test");
        customer.setPassword("123456");
        customer.setAddress("testaddress");
        customer.setEmail("test@gmail.com");
        customer.setUser_id(1); 
    }

    // Add Customer - Success Response
    @Test
    void customer_registration_success() throws Exception {
        Mockito.when(customerService.customerExists(customer)).thenReturn(false);
        Mockito.when(customerService.saveCustomer(customer)).thenReturn(customer);
        
        this.mockMvc.perform(post("/register").flashAttr("customer", customer))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andReturn();
    }
    
    // Add Customer - Customer already exists
    @Test
    void customer_registration_failed_exists() throws Exception {
        Mockito.when(customerService.customerExists(customer)).thenReturn(true);
        
        this.mockMvc.perform(post("/register").flashAttr("customer", customer))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Duplicate datas not allowed."))
                .andDo(print())
                .andReturn();
    }
    
    // Login Customer - Success Response
    @Test
    void customer_login_success() throws Exception {
    	Mockito.when(customerService.validCustomer("SivaK", "123456")).thenReturn(true);
    	Mockito.when(customerService.getCustomerDetails("SivaK")).thenReturn(customer);
    	
    	mockMvc.perform(post("/login").param("username", "SivaK").param("password", "123456"))
    			.andExpect(status().isOk())
    			.andExpect(content().json(objectMapper.writeValueAsString(customer)))
    			.andDo(print())
    			.andReturn();
    }
    
    // Login Customer - InValid user
    @Test
    void customer_login_failed_invaliduser() throws Exception {
    	Mockito.when(customerService.validCustomer("invalidUser", "invalidPassword")).thenReturn(false);
    	
    	mockMvc.perform(post("/login").param("username", "invalidUser").param("password", "invalidPassword"))
    			.andExpect(status().isUnauthorized())
    			.andExpect(content().string("Not a valid user."))
    			.andDo(print())
    			.andReturn();
    }
    
}