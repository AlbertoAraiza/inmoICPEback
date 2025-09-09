package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Dates;

@Repository
public interface DatesDao extends CrudRepository<Dates, Integer>{
	
}
