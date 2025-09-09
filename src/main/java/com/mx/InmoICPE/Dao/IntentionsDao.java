package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Intentions;

@Repository
public interface IntentionsDao extends CrudRepository<Intentions, Integer>{

}
