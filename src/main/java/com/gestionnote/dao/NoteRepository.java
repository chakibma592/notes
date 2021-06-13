package com.gestionnote.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Module;
import com.gestionnote.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}
