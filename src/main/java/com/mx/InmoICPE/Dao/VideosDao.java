package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Videos;

@Repository
public interface VideosDao extends CrudRepository<Videos, Integer>{

}
