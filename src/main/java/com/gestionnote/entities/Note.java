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

@Entity
@Table(name = "notes")
public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double notesession1;
	private double notesession2;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idyear")
	private AnneeUniversitaire year;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmodule")
	private Module module;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpromotion")
	private Promotion promotion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idfiliere")
	private Module filiere;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmatiere")
	private Matiere matiere;
    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "idstudent")
   	private User student;
    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumn(name = "idsemestre")
   	private Semestre semestre;
    public Note() {
		super();
	}
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
	
	
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public AnneeUniversitaire getYear() {
		return year;
	}
	public void setYear(AnneeUniversitaire year) {
		this.year = year;
	}
	public double getNotesession1() {
		return notesession1;
	}
	public void setNotesession1(double notesession1) {
		this.notesession1 = notesession1;
	}
	public double getNotesession2() {
		return notesession2;
	}
	public void setNotesession2(double notesession2) {
		this.notesession2 = notesession2;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	public Module getFiliere() {
		return filiere;
	}
	public void setFiliere(Module filiere) {
		this.filiere = filiere;
	}
	

}
