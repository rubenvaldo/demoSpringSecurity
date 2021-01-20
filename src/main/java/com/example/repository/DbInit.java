package com.example.repository;


import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class DbInit implements CommandLineRunner {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {// throws Exception {
		
		// delete all
		this.userRepository.deleteAll();
		
		// create users
		User ruben = new User("ruben", passwordEncoder.encode("password"), "ADMIN", "ACCESS_TEST1, ACCESS_TEST2" );
		//User ruben = new User("ruben", "password", "ADMIN", "ACCESS_TEST1,ACCESS_TEST2" ); //space after comma - only difference from above
		User junior = new User("junior", passwordEncoder.encode("password"), "EMPLOYEE", "ACCESS_TEST1" );
		User antara = new User("antara", passwordEncoder.encode("password"), "USER", "");
		
		List<User> users = Arrays.asList(ruben, junior, antara);
		
		// save users to db
		this.userRepository.saveAll(users);
		
		
		
	}
	
	

}
