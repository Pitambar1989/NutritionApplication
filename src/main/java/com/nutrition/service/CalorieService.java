package com.nutrition.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.nutrition.exception.NutritionCustomException;
import com.nutrition.model.UserCalorieDetails;
import com.nutrition.util.CalorieCounterConstants;

import lombok.extern.slf4j.Slf4j;

/*
Mifflin-St Jeor Equation:
For men:
BMR = 10W + 6.25H - 5A + 5
For women:
BMR = 10W + 6.25H - 5A - 161
calculate basal metabolic rate (BMR)
*/

/*where:
W is body weight in kg
H is body height in cm
A is age
F is body fat in percentage
*/
//Basal Metabolic Rate (BMR)
@Service
@Slf4j
public class CalorieService {

	public Float calculateBMR(UserCalorieDetails users) throws NutritionCustomException {
		float BMR = 0;
		int age = users.getAge();
		String gender = users.getGender();
		float height = users.getHeight();
		float weight = users.getWeight();
		if ((gender.equals("F")) || (gender.equals("f"))) {
			BMR = (float) (655 + (4.35f * weight) + (4.7f * height) - (4.7f * age));
			log.info("Basal Metabolic Rate:" + BMR);
		} else if ((gender.equals("M")) || (gender.equals("m"))) {
			BMR = (float) (66 + (6.23f * weight) + (12.7f * height) - (6.8f * age));
		} else {
			log.info("Unknown Gender");
			throw new NutritionCustomException(CalorieCounterConstants.GENDER_SELECTION_ERROR);
		}
		return BMRExcercise(BMR, users.getExcercise());
	}

	public float BMRExcercise(float bmrCalc, Integer excerciseNumber) {
		return getExcerciseDetails().get(excerciseNumber) * bmrCalc;
	}

	// 0 = little or no exercise
	// 1 =If you are lightly active (light exercise/sports 1-3 days/week) :
	public Map<Integer, Float> getExcerciseDetails() {
		Map<Integer, Float> map = new HashMap<>();
		map.put(0, 1.2f);
		map.put(1, 1.375f);
		map.put(2, 1.55f);
		map.put(3, 1.275f);
		map.put(4, 1.9f);
		return map;
	}
}
