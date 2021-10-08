package com.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutrition.model.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

	Diet findDietByDietId(int dietId);
	
	Diet findByDietType(String name);

}
