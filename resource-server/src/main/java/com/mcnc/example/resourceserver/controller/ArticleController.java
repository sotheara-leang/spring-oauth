package com.mcnc.example.resourceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcnc.example.resourceserver.dto.ArticleDTO;
import com.mcnc.example.resourceserver.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(method = RequestMethod.GET)
	public List<ArticleDTO> getAllArticles() {
		return articleService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void createArticle(ArticleDTO articleDTO) {
		articleService.create(articleDTO);
//		return new ResponseEntity<ArticleDTO>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteArticle(Integer id) {
		articleService.delete(id);
//		return new ResponseEntity<ArticleDTO>(HttpStatus.OK);
	}
}