package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Roles;

@Repository
public interface RolesDao extends CrudRepository<Roles, Integer>{

}
