package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;
import com.example.repository.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private UserRepository userRepository;
	
	
	
	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("index")
	public String home() {
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("about")
	public String about() {
		return "about";
	}
	
	@GetMapping("services")
	public String services() {
		return "services";
	}
	
	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
		
	}
	
	@PostMapping("process_register")
	public String processRegistration(User user) {
		userRepository.save(user);
		
		return "register_sucess";
		
	}

}
