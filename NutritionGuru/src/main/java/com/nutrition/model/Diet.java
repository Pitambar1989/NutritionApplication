package com.nutrition.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diet_master")
public class Diet implements Serializable {

    private static final long serialVersionUID = 1234567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diet_id")
	private Integer dietId;

	@Column(name = "diet_Type", length = 50)
	private String dietType;

	@OneToMany(mappedBy = "diet", cascade = CascadeType.ALL)
	@Fetch(value=FetchMode.SELECT)
	@JsonIgnore
    private Set<Food> foodList = new HashSet<>();

}
