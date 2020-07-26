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
	
	public List<Article> getPaginatedArticles(int startFrom, int noOfArticles) {

		ArrayList<Article> listOfArticles = new ArrayList<Article>(articleRepository.findAll());
		
		if (noOfArticles > listOfArticles.size()) new ArrayList<Article>();
		
		return listOfArticles.subList(startFrom, startFrom + noOfArticles);
	}
	
	public List<Article> getAllArticles() {

		return articleRepository.findAll();
	}
	
	public Article addArticle(Article article) {

		return articleRepository.save(article);
	}
}
