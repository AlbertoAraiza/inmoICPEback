package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Neighborhoods;

@Repository
public interface NeighborhoodsDao extends CrudRepository<Neighborhoods, Integer>{
	
}
