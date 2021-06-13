package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Filiere;

@Repository
public interface FiliereRepository  extends JpaRepository<Filiere, Long>{

}
