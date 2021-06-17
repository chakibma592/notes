package com.gestionnote.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotions")
public class Promotion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String promotionname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPromotionname() {
		return promotionname;
	}
	public void setPromotionname(String promotionname) {
		this.promotionname = promotionname;
	}
	public Promotion(Long id, String promotionname) {
		super();
		this.id = id;
		this.promotionname = promotionname;
	}
	public Promotion() {
		super();
	}
    
    

}
