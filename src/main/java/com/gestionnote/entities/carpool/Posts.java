package com.gestionnote.entities.carpool;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "posts")
public class Posts implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @ToString.Exclude
	@OneToMany(targetEntity = Comments.class, mappedBy = "posts", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comments> comments= new ArrayList<>();
   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date created_at;
    private String description;
    private int likes;//users_ids
   @ToString.Exclude
   @OneToMany(targetEntity = Picture.class, mappedBy = "posts", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Picture> picturesList;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   	@JoinColumn(name = "category_id", nullable = false)
   	@OnDelete(action = OnDeleteAction.CASCADE)
   	@JsonIgnore
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   	@JoinColumn(name = "user_id", nullable = false)
   	@OnDelete(action = OnDeleteAction.CASCADE)
   	@JsonIgnore
    private UserAccountDetails useraccountdetails ;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "city_id", nullable = false)
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JsonIgnore
   private City city;
   private Double longitude;
   private Double latitude;
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "category_id", nullable = false)
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JsonIgnore
	public Category getCategory() {
		return category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public UserAccountDetails getUseraccountdetails() {
		return useraccountdetails;
	}

	public void setUseraccountdetails(UserAccountDetails useraccountdetails) {
		this.useraccountdetails = useraccountdetails;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Posts() {
    }

  
  

    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	

   

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<Picture> getPicturesList() {
		return picturesList;
	}

	
	public void setPicturesList(List<Picture> picturesList) {
		this.picturesList = picturesList;
	}

	

  
}
