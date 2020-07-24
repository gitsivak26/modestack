package com.modestack.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modestack.assignment.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {}
