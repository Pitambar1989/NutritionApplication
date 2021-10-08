package com.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutrition.model.LoginDetails;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails, Long>{
	LoginDetails findByEmailId(String emailId);
}
