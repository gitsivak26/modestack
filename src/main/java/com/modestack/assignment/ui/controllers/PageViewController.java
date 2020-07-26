package com.modestack.assignment.ui.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.modestack.assignment.services.ArticleService;
import com.modestack.assignment.services.CustomerService;
import com.modestack.assignment.services.SecurityService;

@Controller
public class PageViewController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	SecurityService securityService;
	
	private static final String LOGIN_VIEW = "login";
	private static final String REGISTER_VIEW = "register";
	private static final String ARTICLES_VIEW = "articles";
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, 
			HttpServletResponse response, 
			Model model) {
		
		return LOGIN_VIEW;
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String viewRegister(HttpServletRequest request, 
			HttpServletResponse response, 
			Model model) {
		
		return REGISTER_VIEW;
	}
	
	@RequestMapping(path = "/articles", method = RequestMethod.GET)
	public String viewArticles(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("accessToken") String accessToken,
			Model model) {
		
		if (accessToken != null) {
			String originalValue = securityService.decrypt(accessToken);
			
			model.addAttribute("username", originalValue);
			
			return ARTICLES_VIEW;
		}
		
		return LOGIN_VIEW;
	}
}
