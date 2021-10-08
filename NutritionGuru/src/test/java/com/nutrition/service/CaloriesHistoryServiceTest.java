package com.nutrition.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nutrition.exception.DataNotFoundException;
import com.nutrition.model.LoginDetails;
import com.nutrition.model.WeightGain;
import com.nutrition.model.WeightGainRequest;
import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;
import com.nutrition.repository.WeightGainRepository;
import com.nutrition.repository.WeightLossRepository;

@SpringBootTest
public class CaloriesHistoryServiceTest {

	@Autowired
	CaloriesHistoryServiceImpl caloriesHistoryServiceImpl;

	@MockBean
	WeightLossRepository weightLossRepository;

	@MockBean
	WeightGainRepository weightGainRepository;

	private WeightLoss weightLoss;

	private WeightGain weightGain;
	private WeightGainRequest weightGainRequest;
	private WeightLossRequest weightLossRequest;

	@BeforeEach
	void init() throws ParseException {
		weightLoss = new WeightLoss();
		weightLoss.setCaloriesIntakeDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightLoss.setWeightLossDietId(1);
		weightLoss.setFoodIntakeCategory("Breakfast Time");
		weightLoss.setUserName("John");

		weightGain = new WeightGain();
		weightGain.setCaloriesIntakeDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGain.setWeightGainDietId(1);
		weightGain.setFoodIntakeCategory("Lunch Time");
		weightGain.setUserName("Raj");

		weightGainRequest = new WeightGainRequest();
		weightGainRequest.setFromDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGainRequest.setToDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightGainRequest.setUserName("Raj");

		weightLossRequest = new WeightLossRequest();
		weightLossRequest.setFromDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightLossRequest.setToDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightLossRequest.setUserName("John");

	}

	/*
	 * This method tested for get calories history for weight loss based on date
	 * When valid input
	 */
	@Test
	void testRetrieveCaloriesHistoryForWeightLossWhenValidInputThenSuccessful() {
		List<WeightLoss> weightLossListData = new ArrayList<WeightLoss>();
		weightLossListData.add(weightLoss);
		doReturn(weightLossListData).when(weightLossRepository)
				.findAllByCaloriesIntakeDateBetweenAndUserName(any(Date.class), any(Date.class), any(String.class));
		List<WeightLoss> weightLossList = caloriesHistoryServiceImpl.getCaloriesHistoryForWeightLoss(weightLossRequest);
		assertEquals(weightLossListData, weightLossList);
	}

	
	/*
	 * This method tested for get calories history for weight gain based on date
	 * When valid input
	 */
	@Test
	void testRetrieveCaloriesHistoryForWeightGainWhenValidInputThenSuccessful() {
		List<WeightGain> weightGainListData = new ArrayList<WeightGain>();
		weightGainListData.add(weightGain);
		doReturn(weightGainListData).when(weightGainRepository)
				.findAllByCaloriesIntakeDateBetweenAndUserName(any(Date.class), any(Date.class), any(String.class));
		List<WeightGain> weightGainList = caloriesHistoryServiceImpl.getCaloriesHistoryForWeightGain(weightGainRequest);
		assertEquals(weightGainListData, weightGainList);
	}

}
