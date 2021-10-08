package com.nutrition.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutrition.model.WeightLoss;

@Repository
public interface WeightLossRepository extends JpaRepository<WeightLoss, Long> {
	List<WeightLoss> findAllByCaloriesIntakeDateBetweenAndUserName(Date caloriesIntakeDate, Date caloriesIntakeDate1,
			String userName);
}
