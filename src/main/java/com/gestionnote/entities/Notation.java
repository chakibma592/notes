package com.gestionnote.entities;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Notation {
	private Long id;
	private double notesession1;
	private Module module;
    private User student;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getNotesession1() {
		return notesession1;
	}
	public void setNotesession1(double notesession1) {
		this.notesession1 = notesession1;
	}
		public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public Notation(Long id, double notesession1, Module module, User student) {
		super();
		this.id = id;
		this.notesession1 = notesession1;
		this.module = module;
		this.student = student;
	}
	public Notation() {
		super();
	}
	
    
}
