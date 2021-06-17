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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "modules")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data 
@ToString
public class Module implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String modulename;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsemestre")
	private Semestre semester;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public Semestre getSemester() {
		return semester;
	}
	public void setSemester(Semestre semester) {
		this.semester = semester;
	}
    

    
    
}
