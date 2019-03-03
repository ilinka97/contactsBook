package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.User;
import com.example.service.UserService;
import com.example.validation.UserValidator;

@Controller
@RequestMapping("/")
public class WelcomeController {
	
	private UserService userService;
	private UserValidator userValidator;
	
	@Autowired
	public WelcomeController(UserService userService, UserValidator userValidator) {
		this.userService=userService;
		this.userValidator=userValidator;
	}

	@GetMapping
	public String showWelcomePage(Model model) {
		model.addAttribute("user",new User());
	    return "index";
	}
	
	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		userValidator.validate(user, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "index";
		}
		userService.registerUser(user);
		return "redirect:/?register=true";
	}
	
}
