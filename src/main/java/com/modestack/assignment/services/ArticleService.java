package com.modestack.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modestack.assignment.dao.ArticleRepository;
import com.modestack.assignment.model.Article;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getAllArticles() {
		
		System.out.println("Article Service called...");
		
		return articleRepository.findAll();
	}
	
	public List<Article> addArticle(Article article) {
		
		System.out.println("Adding article to database...");
		
		articleRepository.save(article);
		
		return getAllArticles();
	}
}
