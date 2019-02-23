package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.UserDto;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping
	public String showWelcomePage(Model model) {
		UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "index";
	}
	
}
