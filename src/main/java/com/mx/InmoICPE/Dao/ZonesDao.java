package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Zones;

@Repository
public interface ZonesDao extends CrudRepository<Zones, Integer>{

}
