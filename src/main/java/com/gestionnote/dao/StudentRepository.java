package com.gestionnote.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Module;
import com.gestionnote.entities.Role;
import com.gestionnote.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
