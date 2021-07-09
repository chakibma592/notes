package com.gestionnote.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Filiere;
import com.gestionnote.entities.Module;
import com.gestionnote.entities.Role;
import com.gestionnote.entities.Student;
import com.gestionnote.entities.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	@Query("SELECT s  FROM Student s where s.filiere.id = :idfiliere AND s.promotion.id = :idpromotion")
	ArrayList<Student> findByFilierePromotion(@Param(value="idfiliere")Long idfiliere,@Param(value="idpromotion")Long idpromotion);
	ArrayList<Student> findByFiliere(Filiere filiere);
}
