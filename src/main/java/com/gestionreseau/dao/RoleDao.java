package com.gestionreseau.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gestionreseau.entities.Role;



@Repository
public interface RoleDao extends PagingAndSortingRepository<Role, Integer> {
	
    Page<Role>findByLibelleContainingIgnoreCase(String role, Pageable pageable);
}
