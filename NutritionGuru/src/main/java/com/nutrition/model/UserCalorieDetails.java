package com.nutrition.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nutrition.util.CalorieCounterConstants;

import lombok.Data;

@Data
public class UserCalorieDetails {
	
	@Min(message = CalorieCounterConstants.AGE_ERROR_MESSAGE, value = 1)
	@NotNull(message = CalorieCounterConstants.AGE_ERROR_MESSAGE)
	private Integer age;

	@Size(message = CalorieCounterConstants.GENDER_LENGTH_MSG, min = 1, max = 1)
	@NotNull(message = CalorieCounterConstants.GENDER_NULL_MSG)
	private String gender;

	@Min(message = CalorieCounterConstants.HEIGHT_MIN_ERROR_MSG, value = 50)
	@Max(message = CalorieCounterConstants.HEIGHT_MAX_ERROR_MSG, value = 203)
	@NotNull(message = CalorieCounterConstants.HEIGHT_NULL_MSG)
	private Float height;

	@Min(message = CalorieCounterConstants.WEIGHT_MIN_ERROR_MSG, value = 25)
	@Max(message = CalorieCounterConstants.WEIGHT_MAX_ERROR_MSG, value = 150)
	@NotNull(message = CalorieCounterConstants.WEIGHT_NULL_MSG)
	private Float weight;

	@NotNull(message = CalorieCounterConstants.EXCERCISE_NULL_MSG)
	@Min(message = CalorieCounterConstants.EXCERCISE_MIN_ERROR_MSG, value = 0)
	@Max(message = CalorieCounterConstants.EXCERCISE_MAX_ERROR_MSG, value = 4)
	private Integer excercise;

}
