package com.nutrition.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutrition.model.Food;
@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{

	@Query(value = "select * from food_categories where diet_type_id=?;", nativeQuery = true)
	List<Food> getPlanDetails( Integer dietId);

}
