package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Matiere;

@Repository
public interface MatiereRepository  extends JpaRepository<Matiere, Long>{

}
