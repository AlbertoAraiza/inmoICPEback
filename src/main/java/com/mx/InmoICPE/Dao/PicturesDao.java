package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Pictures;

@Repository
public interface PicturesDao extends CrudRepository<Pictures, Integer>{

}
