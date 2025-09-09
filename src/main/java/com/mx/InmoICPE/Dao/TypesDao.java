package com.mx.InmoICPE.Dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Types;

@Repository
public interface TypesDao extends CrudRepository<Types, Integer>{

}
