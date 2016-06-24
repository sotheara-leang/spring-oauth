package com.mcnc.example.clientapp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class OAuthController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCallBackPage(@RequestParam Map<String, String> params, Map<String, Object> model) {
		model.put("params", params);
		return "oauth/popup";
	}
}
