package com.nutrition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition.model.Diet;
import com.nutrition.model.Food;
import com.nutrition.model.PersonData;
import com.nutrition.repository.DietRepository;
import com.nutrition.repository.FoodRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DietService {

	@Autowired
	private DietRepository dietRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private NutritionGuruService nutritionGuruService;
	
	public List<Food> getDietPlan(PersonData personDetail) {
		String bodyType = nutritionGuruService.getPersonDietType(personDetail);
		Diet diet = dietRepository.findByDietType(bodyType);
		log.info("diet id :"+diet.getDietId());
		return foodRepository.getPlanDetails(diet.getDietId());
	}

	public void saveDietPlan(Diet diet) {
		dietRepository.save(diet);
	}

	public void saveFoodDetails(Food food) {
		foodRepository.save(food);
	}

	public List<Diet> getDietDetails() {
		return dietRepository.findAll();
	}

	public List<Food> getFoodDetails() {
		return foodRepository.findAll();
	}

	
}