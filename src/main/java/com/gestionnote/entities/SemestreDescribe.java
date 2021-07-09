package com.gestionnote.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "semesterdescribes")
public class SemestreDescribe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmodule")
	private Module module;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idfiliere")
	private Filiere filiere;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsemestre")
	private Semestre semestre;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public SemestreDescribe(Long id, Module module, Filiere filiere) {
		super();
		this.id = id;
		this.module = module;
		this.filiere = filiere;
	}
	public SemestreDescribe(Module module, Filiere filiere) {
		super();
		this.module = module;
		this.filiere = filiere;
	}
	public SemestreDescribe() {
		super();
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public SemestreDescribe(Long id, Module module, Filiere filiere, Semestre semestre) {
		super();
		this.id = id;
		this.module = module;
		this.filiere = filiere;
		this.semestre = semestre;
	}
	public SemestreDescribe(Module module, Filiere filiere, Semestre semestre) {
		super();
		this.module = module;
		this.filiere = filiere;
		this.semestre = semestre;
	}
	
	

}
