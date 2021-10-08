package com.nutrition.exception;

public class NutritionCustomException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NutritionCustomException(String errorMessage)
	{
		super(errorMessage);
		
	}

}
