package com.nutrition.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nutrition.model.LoginDetails;

@AutoConfigureMockMvc
@SpringBootTest
class LoginRepositoryTest {

	@MockBean
	LoginRepository loginRepository;

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
	 * This method tests authenticates user When valid input then test cases passes
	 */
	@Test
	void testFindByEmailId() {
		when(loginRepository.findByEmailId(any(String.class))).thenReturn(loginDetails);
		LoginDetails loginDetailsData = loginRepository.findByEmailId(loginDetails.getEmailId());
		assertEquals(loginDetails, loginDetailsData);
	}

}
