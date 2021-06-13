package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{

}
