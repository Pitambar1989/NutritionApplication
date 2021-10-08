package com.nutrition.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutrition.model.WeightGain;

public interface WeightGainRepository extends JpaRepository<WeightGain, Long>{
	List<WeightGain> findAllByCaloriesIntakeDateBetweenAndUserName(Date caloriesIntakeDate,Date toDate,String userName);
}