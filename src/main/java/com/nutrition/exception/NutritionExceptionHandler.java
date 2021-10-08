package com.nutrition.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.nutrition.model.ErrorMessage;


@RestControllerAdvice
public class NutritionExceptionHandler {

	@ExceptionHandler(NutritionCustomException.class)
	public ResponseEntity<ErrorMessage> getInsuranceCustomException(NutritionCustomException exception) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode("INVALID_INPUT_DATA");
		error.setErrMessage(exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode("INVALID_INPUT_DATA");

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String emsg = error.getDefaultMessage();
			errors.put(fieldName, emsg);
			errorMessage.setErrMessage(error.getDefaultMessage());
		});
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstrainValidationExceptions(ConstraintViolationException ex,
			WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage();
		for (ConstraintViolation violation : ex.getConstraintViolations()) {
			errorMessage.setErrMessage(violation.getMessage() + " ");
		}
		errorMessage.setErrorCode("400");
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
