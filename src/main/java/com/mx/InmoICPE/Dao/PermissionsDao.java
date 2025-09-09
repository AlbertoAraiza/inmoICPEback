package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Permissions;

@Repository
public interface PermissionsDao extends CrudRepository<Permissions, Integer>{

}
