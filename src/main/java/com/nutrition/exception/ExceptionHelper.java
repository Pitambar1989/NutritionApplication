package com.nutrition.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

	static Logger logger = Logger.getLogger(ExceptionHelper.class.getName());

	@ExceptionHandler(value = { InvalidInputException.class })
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		logger.error("Invalid Input Exception");
		return new ResponseEntity<Object>("Invalid Input Exception", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = { UserAlreadyExistException.class })
	public ResponseEntity<Object> userAlreadyExistException(UserAlreadyExistException ex) {
		logger.error("User already exists for this email");
		return new ResponseEntity<Object>("User already exists for this email", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { UnauthorizedUser.class })
	public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedUser ex) {
		logger.error("Unauthorized User Exception");
		return new ResponseEntity<Object>("Unauthorized User Exception", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { DataNotFoundException.class })
	public ResponseEntity<Object> handleUserPolicyNotFoundException(DataNotFoundException ex) {
		logger.error("Calories History Data Not Found");
		return new ResponseEntity<Object>("Calories History Data Not Found", HttpStatus.NOT_FOUND);
	}

	

}
