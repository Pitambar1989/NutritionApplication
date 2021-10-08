package com.nutrition.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.model.PersonData;
import com.nutrition.service.NutritionGuruService;

/**
 * @author AYMOMIN
 */


@RestController
@RequestMapping(path = "api")
public class NutritionGuruController {

	@Autowired
	NutritionGuruService nutritionGuruService;

	/**
	 * @param personDetail
	 * @return Diet Type
	 * @throws NutritionGuruException
	 * 
	 */
	@PostMapping("/person/dietType")
	public ResponseEntity<String> personDietType(@Valid @RequestBody PersonData personDetail) {
		String dietType = nutritionGuruService.getPersonDietType(personDetail);
		return new ResponseEntity<>(dietType, HttpStatus.OK);
	}

}
