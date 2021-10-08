package com.nutrition.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_categories")
public class Food implements Serializable {

    private static final long serialVersionUID = 1234567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "foodId")
	private Integer foodId;

	@Column(name = "meal_type")
	private String mealType;

	@Column(name = "time")
	private String time;

	@Column(name = "description")
	private String description;

	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "diet_type_id")
	private Diet diet;

}
