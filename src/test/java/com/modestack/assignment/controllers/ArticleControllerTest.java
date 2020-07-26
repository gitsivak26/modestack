package com.modestack.assignment.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modestack.assignment.model.Article;
import com.modestack.assignment.services.ArticleService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ArticleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ArticleService articleService;
	
	private Article firstArticle, secondArticle, thirdArticle;
	private List<Article> listOfArticles, secondAndThirdArticles;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() {
		firstArticle = new Article();
		firstArticle.setArticle_id(1);
		firstArticle.setAuthor_name("First");
		firstArticle.setTitle("First Article");
		firstArticle.setMessage("This is First Article.");
		
		secondArticle = new Article();
		secondArticle.setArticle_id(1);
		secondArticle.setAuthor_name("Second");
		secondArticle.setTitle("Second Article");
		secondArticle.setMessage("This is Second Article.");
		
		thirdArticle = new Article();
		thirdArticle.setArticle_id(1);
		thirdArticle.setAuthor_name("Third");
		thirdArticle.setTitle("Third Article");
		thirdArticle.setMessage("This is Third Article.");
		
		listOfArticles = new ArrayList<Article>();
		listOfArticles.add(firstArticle);
		listOfArticles.add(secondArticle);
		listOfArticles.add(thirdArticle);
		
		secondAndThirdArticles = new ArrayList<Article>();
		secondAndThirdArticles.add(secondArticle);
		secondAndThirdArticles.add(thirdArticle);
	}
	
	// Get All Articles
	@Test
	void get_all_articles() throws Exception {
		Mockito.when(articleService.getAllArticles()).thenReturn(listOfArticles);
		
		this.mockMvc.perform(get("/api/v1/articles").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(listOfArticles)))
		.andDo(print())
		.andReturn();
	}
	
	// Get Articles - Start from 2nd and number of articles 2
	@Test
	void get_paginated_articles() throws Exception {
		Mockito.when(articleService.getPaginatedArticles(1, 2)).thenReturn(secondAndThirdArticles);
		
		this.mockMvc.perform(get("/api/v1/articles/1/2").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(secondAndThirdArticles)))
		.andDo(print())
		.andReturn();
	}
	
	// Add a new article
	@Test
	void add_article() throws Exception {
		Mockito.when(articleService.addArticle(firstArticle)).thenReturn(firstArticle);
		
		this.mockMvc.perform(post("/api/v1/articles")
				.contentType(MediaType.APPLICATION_JSON)
				.flashAttr("article", firstArticle))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(firstArticle)))
		.andDo(print())
		.andReturn();
	}

}
