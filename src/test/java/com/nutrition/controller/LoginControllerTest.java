package com.nutrition.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrition.exception.InvalidInputException;
import com.nutrition.model.LoginDetails;
import com.nutrition.service.LoginServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest

public class LoginControllerTest {

	@MockBean
	LoginServiceImpl userService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AuthenticationManager authenticationManager;

	private LoginDetails loginDetails;

	@BeforeEach
	void initUserObject() {
		loginDetails = new LoginDetails();
		loginDetails.setEmailId("pitambar2@gmail.com");
		loginDetails.setUsername("Test");
		loginDetails.setPassword("xyz12345");
		loginDetails.setConfPassword("xyz12345");
		loginDetails.setGender("M");

	}

	/*
	 * This method tests createUser When invalid input then bad request
	 */
	@DisplayName("POST /userCreation -Bad Request")
	@Test
	void testCreateUserWhenInvalidInputThenUnsuccessful() throws Exception {
		LoginDetails userDetails = new LoginDetails();
		userDetails.setEmailId("pitambar2@gmail.com");
		doThrow(new InvalidInputException()).when(userService).signUp(any(LoginDetails.class));
		mockMvc.perform(
				post("/userCreation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userDetails)))
				.andExpect(status().isNotFound());
	}

	/*
	 * This method tests createUser When valid input then test cases passes
	 */
	@DisplayName("POST /userCreation")
	@Test
	void testCreateApplicationUserWhenValidInputThenSuccessful() throws Exception {
		doReturn(loginDetails).when(userService).signUp(any(LoginDetails.class));
		when(userService.checkIfUserExist(any(String.class))).thenReturn(Boolean.FALSE);
		mockMvc.perform(post("/userCreation")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDetails)))
				.andExpect(status().isOk()).andExpect(content().contentType("text/plain; charset=UTF-8"));
	}

	/*
	 * This method tests authenticates user When valid input then test cases passes
	 */
	@DisplayName("POST /login")
	@Test
	void testAuthenticate() throws Exception {

		doReturn(new User("pitambar2@gmail.com", "xyz12345", new ArrayList<>())).when(userService)
				.loadUserByUsername("pitambar2@gmail.com");
		doReturn(loginDetails).when(userService).signIn(any(LoginDetails.class));
		Authentication authentication = mock(Authentication.class);
		doReturn(authentication).when(authenticationManager).authenticate(any());
		LoginDetails loginDetails = new LoginDetails();
		loginDetails.setEmailId("pitambar2@gmail.com");
		loginDetails.setPassword("xyz12345");

		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(loginDetails)))
				.andExpect(status().isOk());
	}

	/*
	 * This method is used to convert object to String
	 */
	static String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
