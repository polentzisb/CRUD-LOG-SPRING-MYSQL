package com.polentzi.tvshow.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUser {

	@Email(message = "invalid email")
	@NotBlank(message = "please use an email")
	private String email;

	@NotBlank(message = "password needed")
	@Size(min = 8, max = 20, message = "the password need between 8 and 20 characters ")
	private String password;
	
	
	public LoginUser() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
