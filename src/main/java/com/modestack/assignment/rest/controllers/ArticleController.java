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
		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles());
	}
	
	@GetMapping(path = "/api/v1/articles/{startFrom}/{noOfArticles}")
	public ResponseEntity<?> getPaginatedArticles(@PathVariable("startFrom") int startFrom,
			@PathVariable("noOfArticles") int noOfArticles) {
		
		return ResponseEntity.status(HttpStatus.OK).body(articleService.getPaginatedArticles(startFrom, noOfArticles));
	}
	
	@PostMapping(path = "/api/v1/articles")
	public ResponseEntity<?> addArticle(@ModelAttribute Article article) {

		return ResponseEntity.status(HttpStatus.OK).body(articleService.addArticle(article));
	}	
}
