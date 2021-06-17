package com.gestionnote.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anneeuniversitaires")
public class AnneeUniversitaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String yearname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYearname() {
		return yearname;
	}
	public void setYearname(String yearname) {
		this.yearname = yearname;
	}
    

}
