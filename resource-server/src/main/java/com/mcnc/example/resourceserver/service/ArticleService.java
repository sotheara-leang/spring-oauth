package com.mcnc.example.resourceserver.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.mcnc.example.resourceserver.dto.ArticleDTO;

@Service
public class ArticleService {

	private List<ArticleDTO> articles = new ArrayList<>();
	
	@PostConstruct
	private void init() {
		for (int i = 0; i < 5; i++) {
			ArticleDTO article = new ArticleDTO();
			article.setId(i + 1);
			article.setTitle("Title " + (i + 1));
			article.setContent("Content " + (i + 1));
			articles.add(article);
		}
	}
	
	public List<ArticleDTO> findAll() {
		return articles;
	}
	
	public void create(ArticleDTO article) {
		articles.add(article);
	}
	
	public void delete(Integer id) {
		Iterator<ArticleDTO> iterator = articles.iterator();
		while (iterator.hasNext()) {
			ArticleDTO article = iterator.next();
			if (article.getId() == id) {
				iterator.remove();
				break;
			}
		}
	}
}
