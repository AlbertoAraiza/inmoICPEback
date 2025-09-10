package com.mx.InmoICPE.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PICTURES")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties("realEstate")
public class Pictures {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String url;
	
	@ManyToOne
	@JoinColumn(name = "REAL_ESTATE_ID")
	RealEstates realEstate;
}
