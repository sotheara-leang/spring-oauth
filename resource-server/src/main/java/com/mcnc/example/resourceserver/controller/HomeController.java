package com.mcnc.example.resourceserver.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcnc.example.resourceserver.dto.ArticleDTO;
import com.mcnc.example.resourceserver.service.ArticleService;

@Controller
public class HomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndex(Map<String, Object> model) {
		List<ArticleDTO> articles = articleService.findAll();
		model.put("articles", articles);
		return "index";
	}
}