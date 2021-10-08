package com.nutrition.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.nutrition.model.WeightLoss;
import com.nutrition.model.WeightLossRequest;

@AutoConfigureMockMvc
@SpringBootTest
class WeightLossRepositoryTest {

	@MockBean
	WeightLossRepository weightLossRepository;

	private WeightLoss weightLoss;
	private WeightLossRequest weightLossRequest;

	@BeforeEach
	void init() throws ParseException {
		weightLoss = new WeightLoss();
		weightLoss.setCaloriesIntakeDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2021"));
		weightLoss.setWeightLossDietId(1);
		weightLoss.setFoodIntakeCategory("Breakfast Time");
		weightLoss.setUserName("John");

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
	void testFindAllByCaloriesIntakeDateBetweenAndUserName() {
		List<WeightLoss> weightLossListData = new ArrayList<WeightLoss>();
		weightLossListData.add(weightLoss);
		when(weightLossRepository.findAllByCaloriesIntakeDateBetweenAndUserName(any(Date.class), any(Date.class),
				any(String.class))).thenReturn(weightLossListData);
		List<WeightLoss> weightLossList = weightLossRepository.findAllByCaloriesIntakeDateBetweenAndUserName(
				weightLossRequest.getFromDate(), weightLossRequest.getToDate(), weightLossRequest.getUserName());
		assertEquals(weightLossListData, weightLossList);

	}

}
