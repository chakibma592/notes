package com.gestionnote.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Logincontroller {
	@RequestMapping(value="/signin")
	public String login() {
		return "login";
	}
}
