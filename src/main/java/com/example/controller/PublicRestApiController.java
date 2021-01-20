package com.example.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.repository.UserRepository;

@RestController  
//@Controller
@RequestMapping("api/public")
public class PublicRestApiController { // because it's a RestController it just returns data. It does not have any view
	
	private UserRepository userRepository;
		
	
	public PublicRestApiController(UserRepository userRepository) {
	this.userRepository = userRepository;
}

	@GetMapping("test1")
	public String test1() {
		return "test1";
		
	}
	
	@GetMapping("test2")
	public String test2() {
		return "API Test 2";
		
	}
	
	@GetMapping("test3")
	public String test3() {
		return "API Test 3";
		
	}
	
	@GetMapping("users")
	public List<User> users(){
		return this.userRepository.findAll();
		
	}

}
