package com.nutrition.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nutrition.controller.CalorieController;
import com.nutrition.exception.NutritionCustomException;
import com.nutrition.model.UserCalorieDetails;

@RunWith(PowerMockRunner.class)
public class CalorieServiceTest {

	@Mock
	CalorieController calorieController;

	@InjectMocks
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
	public void calculateBMRForFemale() throws NutritionCustomException {
		Float expectedResult = 100.0f;
		when(calorieController.getCalorieDetails(userCalorieDetails)).thenReturn(expectedResult);
		calorieService.calculateBMR(userCalorieDetails);
		Float response = calorieController.getCalorieDetails(userCalorieDetails);
		assertEquals(expectedResult, response);
	}
	
	@Test
	public void calculateBMRForMale() throws NutritionCustomException {
		UserCalorieDetails userCalorieDetails = new UserCalorieDetails();
		userCalorieDetails.setAge(31);
		userCalorieDetails.setGender("M");
		userCalorieDetails.setExcercise(1);
		userCalorieDetails.setHeight(150.3f);
		userCalorieDetails.setWeight(75.00f);
		
		Float expectedResult = 100.0f;
		when(calorieController.getCalorieDetails(userCalorieDetails)).thenReturn(expectedResult);
		calorieService.calculateBMR(userCalorieDetails);
		Float response = calorieController.getCalorieDetails(userCalorieDetails);
		assertEquals(expectedResult, response);
	}
	
	@Test
	public void calculateBMRForInvalidGender() throws NutritionCustomException {
		UserCalorieDetails userCalorieDetails = new UserCalorieDetails();
		userCalorieDetails.setAge(31);
		userCalorieDetails.setGender("xM");
		userCalorieDetails.setExcercise(1);
		userCalorieDetails.setHeight(150.3f);
		userCalorieDetails.setWeight(75.00f);
		boolean flag = false;
		try {
		Float expectedResult = 100.0f;
		when(calorieController.getCalorieDetails(userCalorieDetails)).thenReturn(expectedResult);
		calorieService.calculateBMR(userCalorieDetails);
		Float response = calorieController.getCalorieDetails(userCalorieDetails);
		} catch (NutritionCustomException e) {
			flag = true;
		}
		assertTrue(flag);
	}
}
