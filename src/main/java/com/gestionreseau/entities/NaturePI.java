package com.gestionreseau.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "naturepi")
public class NaturePI implements Serializable {

	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int id;	
	@NotEmpty
	@NotNull
	private String libelle;	
	@ToString.Exclude
	@OneToMany(targetEntity = PointInteret.class, mappedBy = "naturepi", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<PointInteret> listpointsinterets = new ArrayList<>();
	public NaturePI() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<PointInteret> getListpointsinterets() {
		return listpointsinterets;
	}
	public void setListpointsinterets(List<PointInteret> listpointsinterets) {
		this.listpointsinterets = listpointsinterets;
	}
	
	


}
