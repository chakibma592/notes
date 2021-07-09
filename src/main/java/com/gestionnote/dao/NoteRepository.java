package com.gestionnote.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionnote.entities.Module;
import com.gestionnote.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	@Query("SELECT n FROM Note n where n.filiere.id = :idfiliere AND n.semestre.id = :idsemestre AND n.student.id = :idstudent AND n.promotion.id = :idpromotion Order By n.module.modulename" )
	ArrayList<Note> findByFiliereSemestreEtudiant(@Param(value="idfiliere")Long idfiliere,@Param(value="idsemestre")Long idsemestre,@Param(value="idstudent")int idstudent,@Param(value="idpromotion")Long idpromotion);
	@Query("SELECT n FROM Note n where n.filiere.id = :idfiliere AND n.semestre.id = :idsemestre AND n.student.id = :idstudent AND n.promotion.id = :idpromotion AND n.matiere.id = :idmatiere")
	ArrayList<Note>  Exists(@Param(value="idfiliere")Long idfiliere,@Param(value="idsemestre")Long idsemestre,@Param(value="idstudent")int idstudent,@Param(value="idpromotion")Long idpromotion,@Param(value="idmatiere")Long idmatiere);
}
