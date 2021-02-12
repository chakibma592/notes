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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@ToString
@Data @AllArgsConstructor @NoArgsConstructor
public class User implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private boolean actived;
	private String psc;

	@ToString.Exclude
	@OneToMany(targetEntity=Role.class,mappedBy="user",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
    private List<Role> roles  = new ArrayList<>();







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







	public List<Role> getRoles() {
		return roles;
	}







	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	

	

}
