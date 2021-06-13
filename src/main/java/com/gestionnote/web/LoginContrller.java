package com.gestionnote.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginContrller {

	
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}

    
	@RequestMapping(value="/home")
	public String home(){
		return "home";
	}
	
	
    
    
	@RequestMapping(value="/")
	public String home2(){
		
		return "redirect:/home";
	}
    
    
	@RequestMapping(value="/403")
	public String accessDenied(){
		
		return "403";
	}
    
    
}
