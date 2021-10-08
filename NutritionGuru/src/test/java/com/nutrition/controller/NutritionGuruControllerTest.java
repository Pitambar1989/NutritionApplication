package com.nutrition.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import com.nutrition.model.PersonData;
import com.nutrition.service.NutritionGuruService;

@RunWith(PowerMockRunner.class)
public class NutritionGuruControllerTest {

	@InjectMocks
	NutritionGuruController nutritionGuruController;

	@Mock
	private NutritionGuruService nutritionGuruService;

	private PersonData person;

	@Before
	public void getPersonDetails() {
		person = new PersonData();
		person.setPersonHeight(170);
		person.setPersonWeight(67);
	}

	@Test
	public void testGetPersonDietType() {
		when(nutritionGuruService.getPersonDietType(person)).thenReturn("Stable Weight");
		nutritionGuruController.personDietType(person);
		ResponseEntity<String> response = nutritionGuruController.personDietType(person);
		assertEquals("Stable Weight", response.getBody());
	}

}
