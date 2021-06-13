package com.gestionnote.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.User;



@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer> {
	
   
    
    User findByUsername(String username);
}
