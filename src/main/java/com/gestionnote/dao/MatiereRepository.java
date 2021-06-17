package com.gestionnote.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Matiere;
import com.gestionnote.entities.Module;

@Repository
public interface MatiereRepository  extends JpaRepository<Matiere, Long>{
	ArrayList<Matiere> findByModule(Module module);
}
