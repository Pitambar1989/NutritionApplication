
package com.nutrition.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "diet_master_history")
public class DietMaster implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int diteHistoryId;

	@Column(name = "dm_diet_item1", length = 100)
	private String dietItem1;

	@Column(name = "dm_diet_item2", length = 100)
	private String dietItem2;

	@Column(name = "dm_diet_item3", length = 100)
	private String dietItem3;

	@Column(name = "dm_diet_item4", length = 100)
	private String dietItem4;

	@Column(name = "dm_diet_item5", length = 100)
	private String dietItem5;

	@Column(name = "dm_calories_in_diet", length = 25)
	private String totalCaloriesInDiet;
	
	public int getDiteHistoryId() {
		return diteHistoryId;
	}

	public void setDiteHistoryId(int diteHistoryId) {
		this.diteHistoryId = diteHistoryId;
	}

	public String getDietItem1() {
		return dietItem1;
	}

	public void setDietItem1(String dietItem1) {
		this.dietItem1 = dietItem1;
	}

	public String getDietItem2() {
		return dietItem2;
	}

	public void setDietItem2(String dietItem2) {
		this.dietItem2 = dietItem2;
	}

	public String getDietItem3() {
		return dietItem3;
	}

	public void setDietItem3(String dietItem3) {
		this.dietItem3 = dietItem3;
	}

	public String getDietItem4() {
		return dietItem4;
	}

	public void setDietItem4(String dietItem4) {
		this.dietItem4 = dietItem4;
	}

	public String getDietItem5() {
		return dietItem5;
	}

	public void setDietItem5(String dietItem5) {
		this.dietItem5 = dietItem5;
	}

	public String getTotalCaloriesInDiet() {
		return totalCaloriesInDiet;
	}

	public void setTotalCaloriesInDiet(String totalCaloriesInDiet) {
		this.totalCaloriesInDiet = totalCaloriesInDiet;
	}
}
