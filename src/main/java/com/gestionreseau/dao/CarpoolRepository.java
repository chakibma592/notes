package com.gestionreseau.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionreseau.entities.Carpool;

public interface CarpoolRepository extends JpaRepository<Carpool, Long>{
	
}
