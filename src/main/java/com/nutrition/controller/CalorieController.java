package com.nutrition.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.exception.NutritionCustomException;
import com.nutrition.model.UserCalorieDetails;
import com.nutrition.service.CalorieService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class CalorieController {

	@Autowired
	private CalorieService calorieService;

	@PostMapping(value = "/calorieDetails",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Float getCalorieDetails(@RequestBody @Valid UserCalorieDetails users) throws NutritionCustomException {
		log.info("Hello in Nutrition Portal");
		float usersCalorie = calorieService.calculateBMR(users);
		log.info("Total Calorie:"+usersCalorie);
		return usersCalorie;
	}
}
