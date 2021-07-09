package com.gestionnote.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Matiere;
import com.gestionnote.entities.Module;

@Repository
public interface MatiereRepository  extends JpaRepository<Matiere, Long>{
	ArrayList<Matiere> findByModule(Module module);
	@Query("SELECT m FROM Matiere m where m.module.id = :identity")
	List<Matiere>  findByModuleId(@Param(value="identity")Long id);
}
