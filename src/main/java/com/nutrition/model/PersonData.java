package com.nutrition.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class PersonData {

	@NotNull
	@Min(message = "the  minimum height of the person must be greater then 50 cm", value = 50)
	@Max(message = "the  maximum height of the person must be less then 200 cm", value = 200)
	private float personHeight;

	@NotNull
	@Min(message = "the  minimum weight of the person must be greater then 10", value = 10)
	@Max(message = "the  maximum weight of the person must be less then 200", value = 200)
	private float personWeight;

}
