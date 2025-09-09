package com.mx.InmoICPE.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NEIGHBORHOODS")
@NoArgsConstructor
@AllArgsConstructor
@Data
/*
 * @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 */
public class Neighborhoods {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ZONE_ID")
	Zones zone;
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "neighborhood", cascade = CascadeType.ALL)
	List<RealEstates> realEstates = new ArrayList<>();
}
