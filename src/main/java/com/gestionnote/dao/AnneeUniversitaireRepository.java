package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.AnneeUniversitaire;


@Repository
public interface AnneeUniversitaireRepository  extends JpaRepository<AnneeUniversitaire, Long> {

}
