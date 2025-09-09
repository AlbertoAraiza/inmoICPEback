package com.mx.InmoICPE.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name = "DATES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "APPOINTMENT_DATE_HOUR")
	LocalDateTime appointmentDateHour;
	
	@ManyToOne
	@JoinColumn(name = "ASESOR_ID")
	Users asesor;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	Users customer;
	
	@ManyToOne
	@JoinColumn(name = "REAL_ESTATE_ID")
	RealEstates realEstate;
	
	@ManyToOne
	@JoinColumn(name = "RESULT_ID")
	Results result;
}
