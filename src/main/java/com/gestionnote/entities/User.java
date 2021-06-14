package com.gestionnote.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@ToString
@Data @AllArgsConstructor @NoArgsConstructor
@Table(	name = "users")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String gsm;
	private String adresse;
	private String photo;
	private String cin;
	private String email;
	private boolean actived;
	private String psc;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;





	public int getId() {
		return id;
	}







	public void setId(int id) {
		this.id = id;
	}







	public String getUsername() {
		return username;
	}







	public void setUsername(String username) {
		this.username = username;
	}







	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}







	public boolean isActived() {
		return actived;
	}







	public void setActived(boolean actived) {
		this.actived = actived;
	}







	public String getPsc() {
		return psc;
	}







	public void setPsc(String psc) {
		this.psc = psc;
	}







	







	public String getFirstname() {
		return firstname;
	}







	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}







	public String getLastname() {
		return lastname;
	}







	public void setLastname(String lastname) {
		this.lastname = lastname;
	}







	public String getGsm() {
		return gsm;
	}







	public void setGsm(String gsm) {
		this.gsm = gsm;
	}







	public String getAdresse() {
		return adresse;
	}







	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}







	public String getPhoto() {
		return photo;
	}







	public void setPhoto(String photo) {
		this.photo = photo;
	}







	public String getCin() {
		return cin;
	}







	public void setCin(String cin) {
		this.cin = cin;
	}







	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public Role getRole() {
		return role;
	}







	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	

	

}
