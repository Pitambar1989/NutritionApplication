package com.nutrition.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutrition.model.LoginDetails;
import com.nutrition.exception.DataNotFoundException;
import com.nutrition.exception.InvalidInputException;
import com.nutrition.exception.UnauthorizedUser;
import com.nutrition.exception.UserAlreadyExistException;
import com.nutrition.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

	static Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());
	@Autowired
	private LoginRepository userRepository;

	@Override
	@Transactional
	public LoginDetails signUp(LoginDetails userData) {
			if (checkIfUserExist(userData.getEmailId())) {
				throw new UserAlreadyExistException();
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(userData.getPassword());
			String confPassword = passwordEncoder.encode(userData.getConfPassword());
			userData.setPassword(hashedPassword);
			userData.setConfPassword(confPassword);
			return userRepository.save(userData);
		} 
	

	public boolean checkIfUserExist(String email) {
		return userRepository.findByEmailId(email) != null ? true : false;
	}

	@Override
	public LoginDetails signIn(LoginDetails userData) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		LoginDetails loginDetails = userRepository.findByEmailId(userData.getEmailId());
		boolean checkPass = passwordEncoder.matches(userData.getPassword(), loginDetails.getPassword());
		if (checkPass) {
			return loginDetails;
		} else {
			logger.error("Unauthorized User!");
			throw new UnauthorizedUser();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		LoginDetails loginDetails = userRepository.findByEmailId(emailId);
		if (loginDetails.getEmailId().equalsIgnoreCase(emailId)) {
			return new User(loginDetails.getEmailId(), loginDetails.getPassword(), new ArrayList<>());
		} else {
			logger.error("User not found !");
			throw new UsernameNotFoundException("User not found with username: " + emailId);
		}
	}
}