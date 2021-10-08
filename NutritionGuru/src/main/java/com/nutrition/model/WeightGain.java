package com.nutrition.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "weight_gain_diet_category")
public class WeightGain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wg_diet_id")
	@JsonIgnore
	private int weightGainDietId;

	@Column(name = "wg_user_name", length = 100)
	@JsonIgnore
	private String userName;

	@Column(name = "wg_calories_intake_date")
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date caloriesIntakeDate;

	@Column(name = "wg_food_intake_category", length = 50)
	private String foodIntakeCategory;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "wg_diet_master_id")
	private DietMaster dietMaster;

	public int getWeightGainDietId() {
		return weightGainDietId;
	}

	public void setWeightGainDietId(int weightGainDietId) {
		this.weightGainDietId = weightGainDietId;
	}

	public String getFoodIntakeCategory() {
		return foodIntakeCategory;
	}

	public void setFoodIntakeCategory(String foodIntakeCategory) {
		this.foodIntakeCategory = foodIntakeCategory;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCaloriesIntakeDate() {
		return caloriesIntakeDate;
	}

	public void setCaloriesIntakeDate(Date caloriesIntakeDate) {
		this.caloriesIntakeDate = caloriesIntakeDate;
	}

	public DietMaster getDietMaster() {
		return dietMaster;
	}

	public void setDietMaster(DietMaster dietMaster) {
		this.dietMaster = dietMaster;
	}

}
