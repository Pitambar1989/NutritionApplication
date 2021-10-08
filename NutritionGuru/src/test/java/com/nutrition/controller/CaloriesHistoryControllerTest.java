package com.nutrition.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrition.authentication.JwtTokenUtil;
import com.nutrition.exception.DataNotFoundException;
import com.nutrition.model.LoginDetails;
import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;
import com.nutrition.service.CaloriesHistoryServiceImpl;
import com.nutrition.service.LoginServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class CaloriesHistoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CaloriesHistoryServiceImpl caloriesHistoryService;

	@MockBean
	LoginServiceImpl loginService;

	private WeightLoss weightLoss;

	private WeightGain weightgain;
	private WeightGainRequest weightGainRequest;
	private WeightLossRequest weightLossRequest;

	@BeforeEach
	void init() throws ParseException {
		weightLoss = new WeightLoss();
		weightLoss.setCaloriesIntakeDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightLoss.setWeightLossDietId(1);
		weightLoss.setFoodIntakeCategory("Breakfast Time");
		weightLoss.setUserName("John");

		weightgain = new WeightGain();
		weightgain.setCaloriesIntakeDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightgain.setWeightGainDietId(1);
		weightgain.setFoodIntakeCategory("Lunch Time");
		weightgain.setUserName("John");

		weightGainRequest = new WeightGainRequest();
		weightGainRequest.setFromDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGainRequest.setUserName("Raj");

		weightLossRequest = new WeightLossRequest();
		weightLossRequest.setFromDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightLossRequest.setToDate(new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2021"));
		weightLossRequest.setUserName("John");
	}

	/*
	 * This method tested for get calories history for weight loss based on date
	 * When valid input
	 */
	
	@Test
	void testRetrieveCaloriesHistoryForWeightLossByDateWhenValidInputThenSuccessful() throws Exception {
		List<WeightLoss> weightLossList = new ArrayList<>();
		weightLossList.add(weightLoss);
		doReturn(new User("pitambar2@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
				.loadUserByUsername("pitambar2@gmail.com");
		doReturn(weightLossList).when(caloriesHistoryService)
				.getCaloriesHistoryForWeightLoss(any(WeightLossRequest.class));
		mockMvc.perform(post("/getWeightLossHistory").header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(weightLossRequest)))
				.andExpect(status().isOk());
	}

		
	/*
	 * This method tested for get calories history for weight gain based on date
	 * When valid input
	 */
	@Test
	void testRetrieveCaloriesHistoryForWeightGainByDateWhenValidInputThenSuccessful() throws Exception {
		List<WeightGain> weightGainList = new ArrayList<WeightGain>();
		weightGainList.add(weightgain);
		doReturn(new User("pitambar2@gmail.com", "xyz12345", new ArrayList<>())).when(loginService)
				.loadUserByUsername("pitambar2@gmail.com");
		doReturn(weightGainList).when(caloriesHistoryService)
				.getCaloriesHistoryForWeightGain(any(WeightGainRequest.class));
		mockMvc.perform(post("/getWeightGainHistory").header(HttpHeaders.AUTHORIZATION, "Bearer " + getToken())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(weightGainRequest)))
				.andExpect(status().isOk());
	}

	public static String getToken() {
		UserDetails userDetails = new User("pitambar2@gmail.com", "xyz12345", new ArrayList<>());
		String jwt = new JwtTokenUtil().generateToken(userDetails);
		return jwt;
	}

	static String asJsonString(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);

	}

}
