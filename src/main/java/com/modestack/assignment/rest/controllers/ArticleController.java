package com.modestack.assignment.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.assignment.model.Article;
import com.modestack.assignment.services.ArticleService;

@RestController
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@GetMapping(path = "/api/v1/articles")
	public ResponseEntity<?> getAllArticles() {
		System.out.println("Article GET API called...");
		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
	}
	
	@GetMapping(path = "/api/v1/articles/{start}/{size}")
	public ResponseEntity<?> getPaginatedArticles(@PathVariable("start") int start,
			@PathVariable("size") int size) {
		System.out.println("Article GET API called...");
		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.getPaginatedArticles(start, size));
	}
	
	@PostMapping(path = "/api/v1/articles")
	public ResponseEntity<?> addArticle(@ModelAttribute Article article) {
		
		System.out.println("Article POST API called...");
		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.addArticle(article));
	}
	
}
