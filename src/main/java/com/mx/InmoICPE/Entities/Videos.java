package com.mx.InmoICPE.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VIDEOS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Videos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String url;
	@OneToOne
	@JoinColumn(name = "REAL_ESTATE_ID")
	RealEstates realEstate;
}
