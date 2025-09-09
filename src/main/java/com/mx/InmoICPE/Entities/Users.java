package com.mx.InmoICPE.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	
	@Column(name = "LAST_NAME")
	String lastName;
	String email;
	String pass;
	String phone;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	Roles role;
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "stocker", cascade = CascadeType.ALL)
	List<RealEstates> stockedRealEstates = new ArrayList<>();
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "asesor", cascade = CascadeType.ALL)
	List<Dates> asesorDates = new ArrayList<>();
	
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	List<Dates> customerDates = new ArrayList<>();
}
