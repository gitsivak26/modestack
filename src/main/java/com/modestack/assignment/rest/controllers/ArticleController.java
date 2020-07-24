package com.modestack.assignment.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.assignment.model.Article;
import com.modestack.assignment.services.ArticleService;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
//	@GetMapping(path = "/articles")
//	public ResponseEntity<?> getArticles() {
//		System.out.println("Article GET API called...");
//		
//		return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
//	}
	
	@PostMapping(path = "/articles")
	public ResponseEntity<?> addArticle(@ModelAttribute Article article) {
		
		System.out.println("Article POST API called...");
		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.addArticle(article));
	}
	
}
