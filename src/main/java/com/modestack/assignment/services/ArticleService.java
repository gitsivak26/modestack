package com.modestack.assignment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modestack.assignment.dao.ArticleRepository;
import com.modestack.assignment.model.Article;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getPaginatedArticles(int start, int size) {
		
		System.out.println("Article Service called...");
		
		ArrayList<Article> listOfArticles = new ArrayList<>(articleRepository.findAll());
		
		if (size > listOfArticles.size()) new ArrayList<Article>();
		
		return listOfArticles.subList(start, start + size);
	}
	
	public List<Article> getAllArticles() {
		
		System.out.println("Article Service called...");
		
		return articleRepository.findAll();
	}
	
	public Article addArticle(Article article) {
		
		System.out.println("Adding article to database...");
		
		return articleRepository.save(article);
	}
}
