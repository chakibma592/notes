package com.gestionreseau.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name = "pointinteret")
public class PointInteret  implements Serializable  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

    private Double longitude;

    private Double latitude;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime datePublication;

	private String nomPI;
	private String telephone;
	private String adresse;
   
	@ToString.Exclude
	@OneToMany(targetEntity = Picture.class, mappedBy = "pointinteret", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Picture> listpictures = new ArrayList<>();
    


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_naturepi")
	private NaturePI naturepi;



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}



	public LocalDateTime getDatePublication() {
		return datePublication;
	}



	public void setDatePublication(LocalDateTime datePublication) {
		this.datePublication = datePublication;
	}



	public String getNomPI() {
		return nomPI;
	}



	public void setNomPI(String nomPI) {
		this.nomPI = nomPI;
	}



	public String getTelephone() {
		return telephone;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public List<Picture> getListpictures() {
		return listpictures;
	}



	public void setListpictures(List<Picture> listpictures) {
		this.listpictures = listpictures;
	}



	public NaturePI getNaturepi() {
		return naturepi;
	}



	public void setNaturepi(NaturePI naturepi) {
		this.naturepi = naturepi;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}




}
