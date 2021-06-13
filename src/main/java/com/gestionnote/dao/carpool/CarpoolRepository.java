package com.gestionnote.dao.carpool;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionnote.entities.carpool.Carpool;

public interface CarpoolRepository extends JpaRepository<Carpool, Long>{
	
}
