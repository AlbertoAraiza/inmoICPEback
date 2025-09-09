package com.mx.InmoICPE.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REAL_ESTATES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RealEstates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String address;
	String details;
	@Column(name = "BUILT_LAND")
	int builtLand;
	int land;
	double price;
	@Column(name = "IS_CREDIT")
	int isCredit;
	String facebook;
	String maps;
	
	@ManyToOne
	@JoinColumn(name = "NEIGHBORHOOD_ID")
	Neighborhoods neighborhood;
	
	@ManyToOne
	@JoinColumn(name = "STATUS_ID")
	Status status;
	
	@ManyToOne
	@JoinColumn(name = "TYPE_ID")
	Types type;
	
	@ManyToOne
	@JoinColumn(name = "STOCKER_ID")
	Users stocker;
	
	@ManyToOne
	@JoinColumn(name = "INTENTION_ID")
	Intentions intention;
	
	@OneToOne(mappedBy = "realEstate")
	Videos video;
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
	List<Pictures> pictures = new ArrayList<>();
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL)
	List<Dates> dates = new ArrayList<>();
}
