package com.gestionnote.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "notes")
public class Note implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idyear")
	private AnneeUniversitaire year;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsession")
	private Session session;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmatiere")
	private Matiere matiere;
    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "idstudent")
   	private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "idsemestre")
   	private Student semestre;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Student getSemestre() {
		return semestre;
	}
	public void setSemestre(Student semestre) {
		this.semestre = semestre;
	}
	public AnneeUniversitaire getYear() {
		return year;
	}
	public void setYear(AnneeUniversitaire year) {
		this.year = year;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
    
}
