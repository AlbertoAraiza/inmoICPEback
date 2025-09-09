package com.mx.InmoICPE.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.InmoICPE.Entities.Users;

@Repository
public interface UsersDao extends CrudRepository<Users, Integer>{

}
