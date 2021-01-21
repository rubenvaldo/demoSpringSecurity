package com.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //identity?
	private long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private int active;
	
	private String roles = "";
	
	private String permissions = "";
	
//	public User(String username, String password, String roles, String permissions) {
//		this.username = username;
//		this.password = password;
//		this.roles = roles;
//		this.permissions = permissions;
//		this.active = 1;
//	}
	
	
	public User() {
		
	}
//	protected User() {
//		
//	}

	public User(String firstName, String lastName, String email, String password, int active, String roles,
		String permissions) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.active = active;
	this.roles = roles;
	this.permissions = permissions;
}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getActive() {
		return active;
	}

	public String getRoles() {
		return roles;
	}

	public String getPermissions() {
		return permissions;
	}
	
	public List<String> getRoleList(){
		if(this.roles.length()>0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermissionList(){
		if(this.permissions.length()>0) {
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}

}
