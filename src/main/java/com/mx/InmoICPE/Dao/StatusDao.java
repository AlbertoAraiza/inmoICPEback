package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Status;

@Repository
public interface StatusDao extends CrudRepository<Status, Integer>{

}
