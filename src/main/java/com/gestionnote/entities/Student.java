package com.gestionnote.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "students")
public class Student extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codemassar;
	private String numappoge;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpromotion")
	private Promotion promotion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idfiliere")
	private Filiere filiere;
	public String getCodemassar() {
		return codemassar;
	}
	public void setCodemassar(String codemassar) {
		this.codemassar = codemassar;
	}
	public String getNumappoge() {
		return numappoge;
	}
	public void setNumappoge(String numappoge) {
		this.numappoge = numappoge;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
}
