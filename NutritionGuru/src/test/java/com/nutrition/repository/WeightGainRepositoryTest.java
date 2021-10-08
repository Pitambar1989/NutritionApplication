package com.nutrition.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;

@AutoConfigureMockMvc
@SpringBootTest
class WeightGainRepositoryTest {

	@MockBean
	WeightGainRepository weightGainRepository;

	private WeightGain weightGain;
	private WeightGainRequest weightGainRequest;

	@BeforeEach
	void init() throws ParseException {
		weightGain = new WeightGain();
		weightGain.setCaloriesIntakeDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGain.setWeightGainDietId(1);
		weightGain.setFoodIntakeCategory("Lunch Time");
		weightGain.setUserName("Raj");

		weightGainRequest = new WeightGainRequest();
		weightGainRequest.setFromDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGainRequest.setToDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGainRequest.setUserName("Raj");

	}

	/*
	 * This method tested for get calories history for weight gain based on date
	 * When valid input
	 */
	@Test
	void testFindAllByCaloriesIntakeDateBetweenAndUserName() {
		List<WeightGain> weightGainListData = new ArrayList<WeightGain>();
		weightGainListData.add(weightGain);
		when(weightGainRepository.findAllByCaloriesIntakeDateBetweenAndUserName(any(Date.class), any(Date.class),
				any(String.class))).thenReturn(weightGainListData);
		List<WeightGain> weightGainList = weightGainRepository.findAllByCaloriesIntakeDateBetweenAndUserName(
				weightGainRequest.getFromDate(), weightGainRequest.getToDate(), weightGainRequest.getUserName());
		assertEquals(weightGainListData, weightGainList);
	}
}
