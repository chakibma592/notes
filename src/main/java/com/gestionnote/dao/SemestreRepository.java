package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long>{

}
