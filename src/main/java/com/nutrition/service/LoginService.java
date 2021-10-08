package com.nutrition.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.nutrition.model.LoginDetails;

public interface LoginService {
	public LoginDetails signUp(LoginDetails user);
	 public LoginDetails signIn(LoginDetails user);
	 UserDetails loadUserByUsername(String username);
}
