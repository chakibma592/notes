package com.gestionnote.entities.carpool;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@SuppressWarnings("serial")
@Entity
@Table(name = "carpools")
public class Carpool  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "citystart_id", nullable = false)
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JsonIgnore
    private City start_city;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   	@JoinColumn(name = "cityend_id", nullable = false)
   	@OnDelete(action = OnDeleteAction.CASCADE)
   	@JsonIgnore
    private City end_city;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   	@JoinColumn(name = "useraccountdetails_id", nullable = false)
   	@OnDelete(action = OnDeleteAction.CASCADE)
   	@JsonIgnore
    private UserAccountDetails useraccountdetails;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date start_date;
    private Integer start_hours;
    private Integer start_minutes;
    private Integer available_places;
    private Integer price;    
    private String more_details;   

    public Carpool() {
    }

 

   
    public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public City getStart_city() {
		return start_city;
	}




	public void setStart_city(City start_city) {
		this.start_city = start_city;
	}




	public City getEnd_city() {
		return end_city;
	}




	public void setEnd_city(City end_city) {
		this.end_city = end_city;
	}




	public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Integer getStart_hours() {
        return start_hours;
    }

    public void setStart_hours(Integer start_hours) {
        this.start_hours = start_hours;
    }

    public Integer getStart_minutes() {
        return start_minutes;
    }

    public void setStart_minutes(Integer start_minutes) {
        this.start_minutes = start_minutes;
    }

    public Integer getAvailable_places() {
        return available_places;
    }

    public void setAvailable_places(Integer available_places) {
        this.available_places = available_places;
    }

    public String getMore_details() {
        return more_details;
    }

    public void setMore_details(String more_details) {
        this.more_details = more_details;
    }




	public UserAccountDetails getUseraccountdetails() {
		return useraccountdetails;
	}




	public void setUseraccountdetails(UserAccountDetails useraccountdetails) {
		this.useraccountdetails = useraccountdetails;
	}




	

	
    
}
