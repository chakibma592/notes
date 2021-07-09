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
@Table(name = "matieres")
public class Matiere implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    private String matierename;
	    @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "idmodule")
		private Module module;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getMatierename() {
			return matierename;
		}
		public void setMatierename(String matierename) {
			this.matierename = matierename;
		}
		public Module getModule() {
			return module;
		}
		public void setModule(Module module) {
			this.module = module;
		}
	    
	    
}
