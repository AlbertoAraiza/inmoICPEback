package com.mx.InmoICPE.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ZONES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Zones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	
	@Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
	@ToString.Exclude
	List<Neighborhoods> neighborhoods = new ArrayList<>();
}
