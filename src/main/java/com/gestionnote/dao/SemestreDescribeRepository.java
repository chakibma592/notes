package com.gestionnote.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Module;
import com.gestionnote.entities.SemestreDescribe;
import com.gestionnote.entities.Student;

@Repository
public interface SemestreDescribeRepository extends JpaRepository<SemestreDescribe, Long>{
	@Query("SELECT s.module  FROM SemestreDescribe s where s.filiere.id = :idfiliere AND s.semestre.id = :idsemestre ORDER BY s.module.modulename")
	ArrayList<Module> findByFiliereSemestre(@Param(value="idfiliere")Long idfiliere,@Param(value="idsemestre")Long idsemestre);
}
