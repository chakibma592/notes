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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;
@SuppressWarnings("serial")
@Entity
@Table(name = "useraccountdetails")
public class UserAccountDetails  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String bio;
    private String email;
    private String fullname;
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   	@JoinColumn(name = "city_id", nullable = false)
   	@OnDelete(action = OnDeleteAction.CASCADE)
   	@JsonIgnore
    private City city;
    @ToString.Exclude
   	@OneToMany(targetEntity = Posts.class, mappedBy = "useraccountdetails", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Posts> posts= new ArrayList<>();
    private String profile_image ;
    private String username;
    @ToString.Exclude
   	@OneToMany(targetEntity = Carpool.class, mappedBy = "useraccountdetails", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Carpool> carpoolsList= new ArrayList<>();;

    public UserAccountDetails() {
    }

    public String getBio() {
        return bio;
    }

    

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public String getProfile_image() {
        if(profile_image==null){
            profile_image="https://firebasestorage.googleapis.com/v0/b/ajinsafro-db.appspot.com/o/profile-default.jpg?alt=media&token=430a5da5-9f46-46a0-b816-08ecc26e49fa";
        }
        return profile_image;
    }
   

    public String getUsername() {
        return username;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosts(ArrayList<Posts> posts) {
        this.posts = posts;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

   

    public void setUsername(String username) {
        this.username = username;
    }

	public List<Carpool> getCarpoolsList() {
		return carpoolsList;
	}

	public void setCarpoolsList(ArrayList<Carpool> carpoolsList) {
		this.carpoolsList = carpoolsList;
	}

   
}
