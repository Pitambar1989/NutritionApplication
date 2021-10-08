package com.nutrition.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nutrition.exception.NutritionCustomException;
import com.nutrition.model.UserCalorieDetails;
import com.nutrition.service.CalorieService;

@RunWith(PowerMockRunner.class)
public class CaloriesControllerTest {

	@InjectMocks
	CalorieController calorieController;

	@Mock
	private CalorieService calorieService;
	
	UserCalorieDetails userCalorieDetails = null;
	
	@Before
	public void getUserCalorieDetails() {
		userCalorieDetails = new UserCalorieDetails();
		userCalorieDetails.setAge(31);
		userCalorieDetails.setGender("F");
		userCalorieDetails.setExcercise(1);
		userCalorieDetails.setHeight(150.3f);
		userCalorieDetails.setWeight(75.00f);
	}

	@Test
	public void getCalorieDetails() throws NutritionCustomException {
		Float expectedResult = 100.0f;
		when(calorieService.calculateBMR(userCalorieDetails)).thenReturn(expectedResult);
		calorieController.getCalorieDetails(userCalorieDetails);
		Float response = calorieController.getCalorieDetails(userCalorieDetails);
		assertEquals(expectedResult, response);
	}
}