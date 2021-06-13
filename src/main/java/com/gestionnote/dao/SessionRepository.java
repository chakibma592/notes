package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{

}
