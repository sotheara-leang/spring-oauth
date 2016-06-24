package com.mcnc.example.authserver.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/oauth")
@SessionAttributes("authorizationRequest")
public class OAuthController {

	@RequestMapping("/confirm_access")
	public String getConfirmAccess(Map<String, Object> model) {
		return "oauth/confirm_access";
	}
	
	@RequestMapping("/error")
	public String getError(Map<String, Object> model) {
		return "oauth/error";
	}
}
